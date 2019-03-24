package imagecaching.ikram.com.imageloadingdemo.network.domain;

public class BaseResponse {
    private String stat;
    private String message;
    private int code;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
