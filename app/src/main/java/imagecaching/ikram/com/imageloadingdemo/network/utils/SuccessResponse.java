package imagecaching.ikram.com.imageloadingdemo.network.utils;

/**
 * Created by ikram on 24/03/2019.
 */

public interface SuccessResponse <T>{
    void onSuccess(T response, String requestType);
}
