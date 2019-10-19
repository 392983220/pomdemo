package goal.money.consumerdemo.utils.result;


/**
 * @version 1.0
 * @Author 王先锋
 * @create 2019/10/17 11:19
 */
public class ReturnResultUtil {
    /***
     * 失败带数据
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static ReturnResult returnFailData(Integer code, String message, String data) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setCode(code);
        returnResult.setMessage(message);
        returnResult.setData(data);
        return returnResult;
    }

    /***
     * 失败不带数据
     * @param code
     * @param message
     * @return
     */
    public static ReturnResult returnFail(Integer code, String message) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setCode(code);
        returnResult.setMessage(message);
        return returnResult;
    }

    /***
     * 成功不带数据
     * @param code
     * @param message
     * @return
     */
    public static ReturnResult returnSuccess(Integer code, String message) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setCode(code);
        returnResult.setMessage(message);
        return returnResult;
    }

    /***
     * 成功带数据
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static ReturnResult returnSuccessData(Integer code, String message, Object data) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setCode(code);
        returnResult.setMessage(message);
        returnResult.setData(data);
        return returnResult;
    }
}
