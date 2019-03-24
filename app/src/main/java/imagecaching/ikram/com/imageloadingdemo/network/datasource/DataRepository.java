package imagecaching.ikram.com.imageloadingdemo.network.datasource;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;


public interface DataRepository<T> {

    LiveData<PagedList<T>> getDataList();
    LiveData<NetworkState> getDataLoadStatus();
}
