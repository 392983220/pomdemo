package goal.money.providerdemo.service;

import goal.money.providerdemo.dto.UserInfo;

public interface UserInfoService {
    UserInfo queryByOpenid(String openid);

    int insertSelective(UserInfo record);

    void bindPhone(int phone,String openid);

    UserInfo queryByPhone(int phone);

    void inserPhoneAndPwd(int phone,String password);

    void updatePersonalInfo(UserInfo userInfo);
    //查userInfo表

    int queryUserLevel(int phone);

    void updateExperience(int phone,int experience);

    int queryExperience(int phone);

     int experienceTransformLevel(int experience);

    void updateExperienceLevel(int phone,int experienceLevel);

    void updateUserLevel(int phone);

    void updateBirth(UserInfo userInfo);

}
