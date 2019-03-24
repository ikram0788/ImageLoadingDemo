package imagecaching.ikram.com.imageloadingdemo.network.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

public class DataFactory<T, DS> extends DataSource.Factory<Integer, T> {
    //T->model
    //DS->DataSource
    public MutableLiveData<DS> datasourceLiveData = new MutableLiveData<>();
    private DS dataSource;
    //private JSONObject inputJson;

    public DataFactory(DS dataSource) {
        this.dataSource = dataSource;
        //this.inputJson=inputJson;
    }

    @Override
    public DataSource<Integer, T> create() {
        datasourceLiveData.postValue(this.dataSource);
        return (DataSource<Integer, T>) dataSource;
    }
    public DS getDataSource(){
        return dataSource;
    }
}
