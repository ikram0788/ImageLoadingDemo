package imagecaching.ikram.com.imageloadingdemo.network.datasource;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Response;

public interface ServiceManager<T,RES> {
    Response<RES> callServiceMethod(JSONObject inputJson) throws Throwable;
    Response<RES> callServiceMethod(int page,int perPage) throws Throwable;
    List<T> getDataList();
    Response<RES> getResponse();
    int getStatus();
    int getTotalPage();
    String getMessage();
}
