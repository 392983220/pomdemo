package goal.money.providerdemo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import goal.money.providerdemo.dto.UserInfo;
import goal.money.providerdemo.mapper.UserInfoMapper;
import goal.money.providerdemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo queryByOpenid(String openid) {
        return userInfoMapper.queryByOpenid(openid);
    }

    @Override
    public int insertSelective(UserInfo record) {
        return userInfoMapper.insertSelective(record);
    }

    @Override
    public void bindPhone(int phone, String openid) {
        userInfoMapper.bindPhone(phone,openid);
    }

    @Override
    public UserInfo queryByPhone(int phone) {
        return userInfoMapper.queryByPhone(phone);
    }

    @Override
    public void inserPhoneAndPwd(int phone, String password) {
        userInfoMapper.inserPhoneAndPwd(phone,password);
    }
}
