package goal.money.consumerdemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import goal.money.consumerdemo.contants.UserContant;
import goal.money.consumerdemo.utils.RedisUtils;
import goal.money.consumerdemo.utils.UrlUtils;
import goal.money.consumerdemo.vo.UserVo;
import goal.money.consumerdemo.wx.WxReq;
import goal.money.providerdemo.dto.UserInfo;
import goal.money.providerdemo.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value = "微信")
@Controller
@RequestMapping(value = "weXin")
public class WxController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private WxReq wxReq;
    @Reference
    private UserInfoService userInfoService;

    @GetMapping(value = "login")
    @ApiOperation(value = "微信登陆")
    public String WxLogin() {
        return "redirect:" + wxReq.URL();
    }

    @ResponseBody
    @GetMapping(value = "callBack")
    public String callBack(String code) {
        String resultJson = wxReq.accessToeknUrl(code);
        String resultJsonUrl = UrlUtils.loadURL(resultJson);
        JSONObject jsonObject = JSON.parseObject(resultJsonUrl);
        String accessToken = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");
        String userInfomation = UrlUtils.loadURL(wxReq.userInfoUrl(openid, accessToken));
        UserInfo userInfo = JSON.parseObject(userInfomation, UserInfo.class);
        if (null == userInfoService.queryByOpenid(userInfo.getOpenid())) {
            int flag = userInfoService.insertSelective(userInfo);
            if (flag == 1) {
                redisUtils.set(UserContant.NAME_SPACE + userInfo.getOpenid(), userInfo, 60 * 300);
                return openid;
            } else {
                return null;
            }
        } else {
            return "用户名已存在";
        }
    }

    @ResponseBody
    @GetMapping(value = "bindPhone")
    public String bindPhone(int phone ,String openid){
        userInfoService.bindPhone(phone,openid);
        return "绑定成功";
    }

}
