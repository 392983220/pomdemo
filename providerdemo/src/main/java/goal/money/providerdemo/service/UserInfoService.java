package goal.money.providerdemo.service;

import goal.money.providerdemo.dto.UserInfo;

public interface UserInfoService {
    UserInfo queryByOpenid(String openid);

    int insertSelective(UserInfo record);

    void bindPhone(String phone,String openid);

    UserInfo queryByPhone(String phone);

    void inserPhoneAndPwd(String phone,String password);

    void updatePersonalInfo(UserInfo userInfo);
    //查userInfo表

    int queryUserLevel(String phone);

    void updateExperience(String phone,int experience);

    int queryExperience(String phone);

     int experienceTransformLevel(int experience);

    void updateExperienceLevel(String phone,int experienceLevel);

    void updateUserLevel(String phone);

    void updateBirth(UserInfo userInfo);

    int queryUserPoint(String phone);

}
