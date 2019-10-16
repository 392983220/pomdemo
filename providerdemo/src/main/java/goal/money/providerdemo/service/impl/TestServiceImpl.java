package goal.money.providerdemo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import goal.money.providerdemo.service.TestService;

//这里的@Service要导入阿里巴巴的包
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String testFunction() {
        return "dubboSuccess";
    }
}
