package goal.money.providerdemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("goal.money.providerdemo.mapper")
public class MybatisConfiguration {
}
