package goal.money.consumerdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class UserAop {

/*    @AfterReturning(pointcut = "execution(public * callBack(..))",returning = "openid")
    public String  bindPhone(JoinPoint JoinPoint,Object openid){
        return "redirect:weXin/bindPhone?openid"+openid.toString();
    }*/
}
