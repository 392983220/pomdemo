package goal.money.consumerdemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import goal.money.consumerdemo.contants.UserContant;
import goal.money.consumerdemo.utils.RedisUtils;
import goal.money.providerdemo.dto.UserInfo;
import goal.money.providerdemo.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "账号密码")
@RestController
@RequestMapping(value = "phoneAndPwd")
public class PhoneAndPwdController {
    @Reference
    private UserInfoService userInfoService;
    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "账号密码注册")
    @GetMapping(value = "PhoneAndPwdRegister")
    public String PhoneAndPwdLogin(int phone, String password) {
        if (null == userInfoService.queryByPhone(phone)) {
            userInfoService.inserPhoneAndPwd(phone, password);
            return "注册成功";
        } else {
            return "手机号已存在";
        }
    }

    @ApiOperation(value = "账号密码登陆")
    @GetMapping(value = "phoneAndPwdLogin")
    public String phoneAndPwdLogin(int phone, String password, boolean isAccept) {
        if (!isAccept) {
            return "请点击我同意";
        } else {
            UserInfo userInfo = userInfoService.queryByPhone(phone);
            if (userInfo != null) {
                if (password.equals(userInfo.getPassword())) {
                    redisUtils.set(UserContant.Phone_PWD_NAME_SPACE + phone, userInfo, 60 * 300);
                    return userInfo.toString();
                } else {
                    return "用户名或密码不对";
                }
            } else {
                return "用户名不存在";
            }
        }
    }


}
