package goal.money.consumerdemo.utils.result;


import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @Author 王先锋
 * @create 2019/10/17 11:19
 */
@Data
public class ReturnResult<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
}
