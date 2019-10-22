package goal.money.providerdemo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import goal.money.providerdemo.dto.UserInfo;
import goal.money.providerdemo.mapper.UserInfoMapper;
import goal.money.providerdemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @authorZhouWeiPing
 * @date10/22
 */
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
    public UserInfo queryByPhone(String phone) {
        return userInfoMapper.queryByPhone(phone);
    }

    @Override
    public void bindPhone(String phone, String openid) {
        userInfoMapper.bindPhone(phone,openid);
    }

    @Override
    public int queryUserLevel(String phone) {
        return userInfoMapper.queryUserLevel(phone);
    }

    @Override
    public void updateExperience(String phone, int experience) {
        userInfoMapper.updateExperience(phone,experience);
    }

    @Override
    public int queryExperience(String phone) {
        return userInfoMapper.queryExperience(phone);
    }

    @Override
    public void experienceTransformLevel(String phone, int experience) {
        userInfoMapper.experienceTransformLevel(phone,experience);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo record) {
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateBirth(UserInfo userInfo) {
        userInfoMapper.updateBirth(userInfo);
    }


}
