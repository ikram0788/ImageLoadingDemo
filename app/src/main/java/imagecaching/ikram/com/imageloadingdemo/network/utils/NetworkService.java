package imagecaching.ikram.com.imageloadingdemo.network.utils;

import io.reactivex.functions.Consumer;

/**
 * Created by ikram on 24/03/2019.
 */

public class NetworkService<T> implements Consumer<T> {
    private String requestType;
    private ErrorResponse errorCallback;
    private SuccessResponse successCallback;

    public NetworkService(String requestType, SuccessResponse<? extends T> successCallback, ErrorResponse<? extends Throwable> errorCallback) {
        this.requestType = requestType;
        this.successCallback = successCallback;
        this.errorCallback = errorCallback;
    }

 /*   public NetworkService(final RequestType requestType, final NetworkServiceCall<T1, T2, RequestType> serviceCallback) {
        this.requestType = requestType;
        this.serviceCallback = serviceCallback;
        new Consumer<T2>() {

            @Override
            public void accept(T2 t2) throws Exception {
                serviceCallback.onError(t2, requestType);
            }
        };
    }*/


    @Override
    public void accept(T t) {
        if (t instanceof Throwable) {
            errorCallback.onError(t, requestType);
        } else {
            successCallback.onSuccess(t, requestType);
        }
        //serviceCallback.onSuccess(t, requestType);
    }
}
