package goal.money.consumerdemo.vo;

import java.io.Serializable;

/**
 * @authorZhouWeiPing
 * @date2019/10/19
 */
public class PersonalInfo implements Serializable {
    private String nickname;
    private int sex;
    private Long birth;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Long getBirth() {
        return birth;
    }

    public void setBirth(Long birth) {
        this.birth = birth;
    }
}
