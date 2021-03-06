package goal.money.consumerdemo.vo;

import io.swagger.annotations.ApiParam;

import java.io.Serializable;

public class UserVo implements Serializable {
    @ApiParam(hidden = true)
    private Long userId;
    @ApiParam(hidden = true)
    private String phone;
    @ApiParam(hidden = true)
    private String password;
    @ApiParam(hidden = true)
    private String openid;
    @ApiParam(hidden = true)
    private String nickname;
    @ApiParam(hidden = true)
    private Integer sex;
    @ApiParam(hidden = true)
    private String language;
    @ApiParam(hidden = true)
    private String city;
    @ApiParam(hidden = true)
    private String province;
    @ApiParam(hidden = true)
    private String headimgurl;
    @ApiParam(hidden = true)
    private Integer accumulatePoint;
    @ApiParam(hidden = true)
    private Integer experience;
    @ApiParam(hidden = true)
    private Integer experienceLevel;
    @ApiParam(hidden = true)
    private Integer userLevel;
    @ApiParam(hidden = true)
    private Long userBirth;
    @ApiParam(hidden = true)
    private Integer birthIsUpdate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Integer getAccumulatePoint() {
        return accumulatePoint;
    }

    public void setAccumulatePoint(Integer accumulatePoint) {
        this.accumulatePoint = accumulatePoint;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(Integer experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Long getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(Long userBirth) {
        this.userBirth = userBirth;
    }

    public Integer getBirthIsUpdate() {
        return birthIsUpdate;
    }

    public void setBirthIsUpdate(Integer birthIsUpdate) {
        this.birthIsUpdate = birthIsUpdate;
    }
}
