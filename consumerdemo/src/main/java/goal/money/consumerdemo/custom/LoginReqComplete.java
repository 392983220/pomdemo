package goal.money.consumerdemo.custom;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import goal.money.consumerdemo.contants.UserContant;
import goal.money.consumerdemo.utils.RedisUtils;
import goal.money.consumerdemo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class LoginReqComplete implements HandlerInterceptor {
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        if (methodAnnotation != null) {
            String wxToken = request.getHeader("wxToken");  // 从 http 请求头中取出 token
            String userToken = request.getHeader("userToken");
           String userJsonStr =  (String) redisUtils.get(UserContant.NAME_SPACE+(StringUtils.isNotEmpty(userToken)?userToken:wxToken));
            UserVo userVo= JSONObject.parseObject(userJsonStr, UserVo.class);
            if (userVo!=null){
                request.setAttribute("wxToken", userVo);
            }else {
                //throw  new RuntimeException("login error");
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
