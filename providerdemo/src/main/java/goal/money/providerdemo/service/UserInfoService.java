package goal.money.providerdemo.service;

import goal.money.providerdemo.dto.UserInfo;

/**
 * @authorZhouWeiPing
 * @date10/22
 */
public interface UserInfoService {

    UserInfo queryByOpenid(String openid);

    int insertSelective(UserInfo record);

    UserInfo queryByPhone(String phone);

    void bindPhone(String phone, String openid);

    int queryUserLevel(String phone);

    void updateExperience(String phone,int experience);

    int queryExperience(String phone);

    void experienceTransformLevel(String phone,int experience);

    int updateByPrimaryKeySelective(UserInfo record);

    void updateBirth(UserInfo userInfo);
}
