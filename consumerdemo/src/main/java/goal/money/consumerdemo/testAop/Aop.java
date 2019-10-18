package goal.money.consumerdemo.testAop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Aop {

    @AfterReturning(pointcut = "execution(public * login(..))",returning = "returningValue")
    public void testAop(JoinPoint joinPoint,Object returningValue) {
        System.out.println("目标对象：" + joinPoint.getTarget() + "方法名:" + joinPoint.getSignature().getName() + "参数列表：" + Arrays.toString(joinPoint.getArgs())
        +"返回值:"+returningValue);
    }
}
