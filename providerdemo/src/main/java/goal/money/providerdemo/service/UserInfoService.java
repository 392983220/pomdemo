package goal.money.providerdemo.service;

import goal.money.providerdemo.dto.UserInfo;

public interface UserInfoService {
    UserInfo queryByOpenid(String openid);

    int insertSelective(UserInfo record);

    void bindPhone(int phone,String openid);

    UserInfo queryByPhone(int phone);

    void inserPhoneAndPwd(int phone,String password);

}
