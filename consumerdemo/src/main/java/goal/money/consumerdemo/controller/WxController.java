package goal.money.consumerdemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import goal.money.consumerdemo.contants.UserContant;
import goal.money.consumerdemo.custom.CurrentUser;
import goal.money.consumerdemo.custom.LoginRequired;
import goal.money.consumerdemo.utils.RedisUtils;
import goal.money.consumerdemo.utils.UrlUtils;
import goal.money.consumerdemo.utils.result.ReturnResult;
import goal.money.consumerdemo.utils.result.ReturnResultUtil;
import goal.money.consumerdemo.vo.PersonalInfo;
import goal.money.consumerdemo.vo.UserVo;
import goal.money.consumerdemo.wx.WxReq;
import goal.money.providerdemo.dto.UserInfo;
import goal.money.providerdemo.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
    public ReturnResult callBack(String code) {
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
                return ReturnResultUtil.returnSuccessData(1,"openid",openid);
            } else {
                return null;
            }
        } else {
            return ReturnResultUtil.returnFail(2,"用户名已存在");
        }
    }

    @ResponseBody
    @GetMapping(value = "bindPhone")
    @LoginRequired
    public ReturnResult bindPhone(String phone, @CurrentUser UserVo userVo) {
        if (null == userInfoService.queryByPhone(phone)) {
            userInfoService.bindPhone(phone, userVo.getOpenid());
            if (userInfoService.queryUserLevel(phone) == 0) {
                userInfoService.updateExperience(phone, 10);
                int experience = userInfoService.queryExperience(phone);
                int experienceLevel = userInfoService.experienceTransformLevel(experience);
                userInfoService.updateExperienceLevel(phone, experienceLevel);
                if (experience >= 180) {
                    userInfoService.updateUserLevel(phone);
                }
            }
            return ReturnResultUtil.returnSuccessData(1,"绑定成功",userInfoService.queryByPhone(phone));
        } else {
            return ReturnResultUtil.returnFail(2,"改手机号已被注册");
        }
    }

    @ResponseBody
    @GetMapping(value = "updatePersonalInfo")
    @LoginRequired
    public ReturnResult updatePersonalInfo(@CurrentUser UserVo userVo, PersonalInfo personalInfo) {
        userVo.setNickname(personalInfo.getNickname());
        userVo.setSex(personalInfo.getSex());
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userVo, userInfo);
        userInfoService.updatePersonalInfo(userInfo);
        if (userVo.getBirthIsUpdate() == 0) {
            userVo.setUserBirth(personalInfo.getBirth());
            userInfoService.updateBirth(userInfo);
        } else {
            return ReturnResultUtil.returnFail(2,"出生日期已修改过，不能再次修改");
        }
        return ReturnResultUtil.returnSuccessData(1,"绑定成功",userInfoService.queryByPhone(userVo.getPhone()));
    }

}
