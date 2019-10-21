package goal.money.consumerdemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Maps;
import goal.money.consumerdemo.custom.CurrentUser;
import goal.money.consumerdemo.custom.LoginRequired;
import goal.money.consumerdemo.utils.ActiveMQUtils;
import goal.money.consumerdemo.utils.RedisUtils;
import goal.money.consumerdemo.utils.WxPayUtils;
import goal.money.consumerdemo.vo.UserVo;
import goal.money.consumerdemo.wx.WxApi;
import goal.money.consumerdemo.wx.WxPay;
import goal.money.providerdemo.dto.OrderInfo;
import goal.money.providerdemo.dto.Recycle;
import goal.money.providerdemo.service.OrderInfoService;
import goal.money.providerdemo.service.RecycleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

@Api(tags = "微信支付")
@RestController
@RequestMapping(value = "wxPay")
public class WxPayController {
    @Autowired
    private WxApi wxApi;

    @Autowired
    private WxPay wxPay;

    @Reference
    private OrderInfoService orderInfoService;
    @Reference
    private RecycleService recycleService;
    @Autowired
    private ActiveMQUtils activeMQUtils;

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "微信支付")
    @GetMapping(value = "wxpay")
    @LoginRequired
    public String wxPay(OrderInfo orderInfo, @CurrentUser UserVo userVo) throws Exception {
        if (null != redisUtils.get(userVo.getPhone())) {
            return wxApi.wxPay(orderInfo);
        } else {
            Recycle recycle = new Recycle();
            BeanUtils.copyProperties(orderInfo, recycle);
            recycleService.insertSelective(recycle);
            orderInfoService.deleteByPrimaryKey(orderInfo.getOrderId());
            return "订单已过期";
        }
    }

    @RequestMapping(value = "returnBack")
    public void returnBack(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        InputStream inputStream = httpServletRequest.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        bufferedReader.close();
        inputStream.close();
        Map<String, String> resultMap = WxPayUtils.xmlToMap(sb.toString());
        //成功回调了
        if ("SUCCESS".equals(resultMap.get("return_code"))) {
            //验证签名与金额
            boolean isCheckSign = WxPayUtils.checkSign(resultMap, wxPay.getKey());
            if (isCheckSign) {
                String orderNo = resultMap.get("out_trade_no");


                activeMQUtils.sendQueueMesage("test", orderNo);


               /* orderInfoService.updateOrderState(orderNo);*///修改订单状态为"2"   待发货
                Map<String, String> rMap = Maps.newHashMap();
                rMap.put("return_code", "SUCCESS");
                rMap.put("return_msg", "OK");
                String xml = WxPayUtils.mapToXml(rMap);
                httpServletResponse.getWriter().write(xml);
            }
        }
    }

}
