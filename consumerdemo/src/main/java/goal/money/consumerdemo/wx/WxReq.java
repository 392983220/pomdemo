package goal.money.consumerdemo.wx;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wx")
public class WxReq {

    private String codeUrl;
    private String appid;
    private String redirect_uri;
    private String code;
    private String scope;
    private String access_token;
    private String secret;
    private String userInfoUrl;

    public String getUserInfoUrl() {
        return userInfoUrl;
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String URL(){
        StringBuffer buffer = new StringBuffer(getCodeUrl());
        buffer.append("appid=").append(getAppid())
                .append("&redirect_uri=").append(getRedirect_uri())
                .append("&response_type=").append(getCode())
                .append("&scope=").append(getScope())
                .append("&state=STATE").append("#wechat_redirect");
        return buffer.toString();
    }

    public String accessToeknUrl(String code){
        StringBuffer buffer = new StringBuffer(getAccess_token());
        buffer.append("appid=").append(getAppid())
                .append("&secret=").append(getSecret())
                .append("&code=").append(code)
                .append("&grant_type=").append("authorization_code");
        return buffer.toString();
    }
    public String userInfoUrl(String openid,String accessToken){
        StringBuffer buffer = new StringBuffer(getUserInfoUrl());
        buffer.append("access_token=").append(accessToken)
                .append("&openid=").append(openid);
        return buffer.toString();
    }
}
