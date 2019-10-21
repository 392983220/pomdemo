package goal.money.consumerdemo.testAop;

import goal.money.consumerdemo.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("aop测试")
@RequestMapping(value = "testAop")
public class AopController {

    @GetMapping(value = "login")
    @ApiOperation(value = "登陆")
    public UserVo login(String phone,String password){
        UserVo userVo=new UserVo();
        userVo.setPhone(phone);
        userVo.setPassword(password);
        return userVo;
    }
}
