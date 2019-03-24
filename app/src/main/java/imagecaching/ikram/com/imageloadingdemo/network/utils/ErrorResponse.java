package imagecaching.ikram.com.imageloadingdemo.network.utils;

/**
 * Created by ikram on 24/03/2019.
 */

public interface ErrorResponse<T> {
    void onError(T error, String requestType);
}
