package goal.money.consumerdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

/**
 * @authorwxf
 * @date10/21
 */
@Component
@ConfigurationProperties(prefix ="cate")
@Data
public class NameAndUrl implements Serializable {
    private static final long serialVersionUID = -2068751299199793508L;
    private Map<String,String> map;
    private String defaultMsg;
    private Integer cartNum;
}
