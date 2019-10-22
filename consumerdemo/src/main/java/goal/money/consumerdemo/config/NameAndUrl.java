package goal.money.consumerdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @authorwxf
 * @date10/21
 */
@Component
@ConfigurationProperties(prefix ="cate")
@Data
public class NameAndUrl {
    private Map<String,String> map;
    private String defaultMsg;
}
