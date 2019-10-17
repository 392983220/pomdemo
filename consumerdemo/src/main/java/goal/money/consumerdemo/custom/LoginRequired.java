package goal.money.consumerdemo.custom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)//代表了这个自定义注解必须要加载方法上
@Retention(RetentionPolicy.RUNTIME)//运行时候有效
public @interface LoginRequired {
}
