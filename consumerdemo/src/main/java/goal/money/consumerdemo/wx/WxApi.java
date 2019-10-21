package goal.money.consumerdemo.wx;

import com.alibaba.dubbo.config.annotation.Reference;
import goal.money.consumerdemo.utils.CommonUtil;
import goal.money.consumerdemo.utils.WxPayUtils;
import goal.money.providerdemo.dto.OrderInfo;
import goal.money.providerdemo.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static goal.money.consumerdemo.utils.UrlUtils.doPost;
import static goal.money.consumerdemo.utils.WxPayUtils.mapToXml;
import static goal.money.consumerdemo.utils.WxPayUtils.xmlToMap;

@Service
public class WxApi {
    @Autowired
    private WxPay wxPay;
    @Reference
    private OrderInfoService orderInfoService;

    public String wxPay(OrderInfo orderInfo) throws Exception {
        SortedMap<String, String> param = new TreeMap<String, String>();
        param.put("appid", wxPay.getAppid());
        param.put("mch_id", wxPay.getMchid());
        param.put("nonce_str", CommonUtil.createUUID(32));
        param.put("body", String.valueOf(orderInfo.toString()));
        param.put("out_trade_no", orderInfo.getOrderNumber());//订单编号
        param.put("total_fee", String.valueOf(orderInfo.getSumPrice()));
        param.put("spbill_create_ip", "192.168.1.145");
        param.put("notify_url", wxPay.getNotifyurl());
        param.put("trade_type", wxPay.getType());
        String sign = WxPayUtils.generateSignature(param, wxPay.getKey());
        param.put("sign", sign);
        String paramXml = mapToXml(param);
        String resultXml = doPost(wxPay.getUnified(), paramXml, 5000);
        Map<String, String> resultStr = xmlToMap(resultXml);
        if (null != resultStr) {
            return resultStr.get("code_url");
        }
        return null;
    }
}
