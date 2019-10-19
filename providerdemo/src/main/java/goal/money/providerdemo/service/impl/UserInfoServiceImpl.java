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
        userInfoMapper.bindPhone(phone, openid);
    }

    @Override
    public UserInfo queryByPhone(int phone) {
        return userInfoMapper.queryByPhone(phone);
    }

    @Override
    public void inserPhoneAndPwd(int phone, String password) {
        userInfoMapper.inserPhoneAndPwd(phone, password);
    }

    @Override
    public void updatePersonalInfo(UserInfo userInfo) {
        userInfoMapper.updatePersonalInfo(userInfo);
    }



    @Override
    public int queryUserLevel(int phone) {
        return userInfoMapper.queryUserLevel(phone);
    }

    @Override
    public void updateExperience(int phone, int experience) {
        userInfoMapper.updateExperience(phone, experience);
    }

    @Override
    public int queryExperience(int phone) {
        return userInfoMapper.queryExperience(phone);
    }


    public int experienceTransformLevel(int experience) {
        if (experience >= 90)
            return 3;
        else if (experience >30)
            return 2;
        else return 1;
    }

    @Override
    public void updateExperienceLevel(int phone, int experienceLevel) {
        userInfoMapper.updateExperienceLevel(phone,experienceLevel);
    }

    @Override
    public void updateUserLevel(int phone) {
        userInfoMapper.updateUserLevel(phone);
    }

    @Override
    public void updateBirth(UserInfo userInfo) {
        userInfoMapper.updateBirth(userInfo);
    }
}
