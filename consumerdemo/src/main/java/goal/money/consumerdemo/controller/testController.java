package goal.money.consumerdemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import goal.money.consumerdemo.utils.RedisUtils;
import goal.money.providerdemo.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "集成测试")
@RestController
@RequestMapping(value = "test")
public class testController {
    //@Reference导入的阿里巴巴的包
    @Reference
    private TestService testService;
    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "测试redis")
    @GetMapping(value = "testRedis")
    public String testRedis(){
        redisUtils.set("test","success",60);
        return redisUtils.get("test").toString();
    }
    @ApiOperation(value = "测试dubbo")
    @GetMapping(value = "testDubbo")
    public String testDubbo(){
        return testService.testFunction();
    }
}
