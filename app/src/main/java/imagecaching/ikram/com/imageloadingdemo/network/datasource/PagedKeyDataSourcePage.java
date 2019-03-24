package imagecaching.ikram.com.imageloadingdemo.network.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import org.json.JSONObject;

import java.io.IOException;

import static imagecaching.ikram.com.imageloadingdemo.network.utils.Constants.ContentState.LOADING;
import static imagecaching.ikram.com.imageloadingdemo.network.utils.Constants.ContentState.CONTENT;
import static imagecaching.ikram.com.imageloadingdemo.network.utils.Constants.ContentState.ERROR_NETWORK;
import static imagecaching.ikram.com.imageloadingdemo.network.utils.Constants.ContentState.ERROR_GENERAL;

public class PagedKeyDataSourcePage<T> extends PageKeyedDataSource<Integer, T> {
    private ServiceManager serviceManager;
    public MutableLiveData<NetworkState> networkStateMutableLiveData;
    private JSONObject inputObject;
    private int PAGE_SIZE = 10;

    public PagedKeyDataSourcePage(ServiceManager service, JSONObject inputObject) {
        networkStateMutableLiveData = new MutableLiveData<NetworkState>();
        this.serviceManager = service;
        this.inputObject = inputObject;
        try {
            PAGE_SIZE = inputObject.getInt("page");
        } catch (Exception e) {
        }
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, T> callback) {
        //Completable.fromAction(() -> loadInitial(params,callback));
        try {
            networkStateMutableLiveData.postValue(new NetworkState(LOADING, "Loading"));
            serviceManager.callServiceMethod(0, PAGE_SIZE);
            if (serviceManager.getResponse() != null) {
                if (serviceManager.getDataList().size() > 0) {
                    networkStateMutableLiveData.postValue(new NetworkState(CONTENT, serviceManager.getMessage()));
                    callback.onResult(serviceManager.getDataList(), 0, 0 + 1);

                } else {
                    networkStateMutableLiveData.postValue(new NetworkState(ERROR_GENERAL, serviceManager.getMessage()));
                }
            } else {
                callback.onResult(null, null, 0 + 1);
            }
        } catch (IOException ex) {
            networkStateMutableLiveData.postValue(new NetworkState(ERROR_NETWORK, ex.getMessage()));
        } catch (Throwable throwable) {
            networkStateMutableLiveData.postValue(new NetworkState(ERROR_GENERAL, throwable.getMessage()));
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, T> callback) {
        /*networkStateMutableLiveData.postValue(new NetworkState(LOADING, "Loading"));
        try {
            inputObject.put("offset", params.key);
            inputObject.put("page", params.requestedLoadSize);
        } catch (Exception e) {
        }
        try {
            if (!(serviceManager.getTotalPage() > params.key)) {
                return;
            }
            serviceManager.callServiceMethod(params.key, PAGE_SIZE);
            if (serviceManager.getResponse() != null) {
                if (serviceManager.getDataList().size() > 0) {
                    networkStateMutableLiveData.postValue(new NetworkState(serviceManager.getDataList().size() < PAGE_SIZE ? CONTENT : LOADING, serviceManager.getDataList().size() < PAGE_SIZE ? "There is no more records" : "Loading"));
                    callback.onResult(serviceManager.getDataList(), params.key + 1);

                } else {
                    networkStateMutableLiveData.postValue(new NetworkState(CONTENT, "There is no more records"));
                }
            } else {
                callback.onResult(null, params.key + 1);
            }
        } catch (IOException ex) {
            networkStateMutableLiveData.postValue(new NetworkState(ERROR_NETWORK, ex.getMessage()));
        } catch (Throwable throwable) {
            networkStateMutableLiveData.postValue(new NetworkState(ERROR_GENERAL, throwable.getMessage()));
        }*/
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, T> callback) {
        networkStateMutableLiveData.postValue(new NetworkState(LOADING, "Loading"));
        try {
            inputObject.put("offset", params.key);
            inputObject.put("page", params.requestedLoadSize);
        } catch (Exception e) {
        }
        try {
            serviceManager.callServiceMethod(params.key, PAGE_SIZE);
            if (serviceManager.getResponse() != null) {
                if (serviceManager.getDataList().size() > 0) {
                    networkStateMutableLiveData.postValue(new NetworkState(serviceManager.getDataList().size() < PAGE_SIZE ? CONTENT : LOADING, serviceManager.getDataList().size() < PAGE_SIZE ? "There is no more records" : "Loading"));
                    callback.onResult(serviceManager.getDataList(), params.key + 1);
                } else {
                    networkStateMutableLiveData.postValue(new NetworkState(CONTENT, "There is no more records"));
                }
            } else {
                callback.onResult(null, params.key + 1);
            }
        } catch (IOException ex) {
            networkStateMutableLiveData.postValue(new NetworkState(ERROR_NETWORK, ex.getMessage()));
        } catch (Throwable throwable) {
            networkStateMutableLiveData.postValue(new NetworkState(ERROR_GENERAL, throwable.getMessage()));
        }
    }
}
