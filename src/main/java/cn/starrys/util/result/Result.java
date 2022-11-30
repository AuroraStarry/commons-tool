package cn.starrys.util.result;

/**
 * @author XingKong
 * <p>
 * creationTime: 2022/11/30 23:02
 */
public class Result<T> {

    /**
     * 执行状态
     */
    private boolean state;

    /**
     * 状态代码
     */
    private Integer code;

    /**
     * 执行消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public Result() {
    }

    public Result(boolean state, Integer code, String message, T data) {
        this.state = state;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "state=" + state +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
