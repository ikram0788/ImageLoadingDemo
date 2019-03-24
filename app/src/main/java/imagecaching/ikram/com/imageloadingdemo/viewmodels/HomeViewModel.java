package imagecaching.ikram.com.imageloadingdemo.viewmodels;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import org.json.JSONObject;

import javax.inject.Inject;

import imagecaching.ikram.com.imageloadingdemo.datamodels.Photo;
import imagecaching.ikram.com.imageloadingdemo.network.datasource.DataRepository;
import imagecaching.ikram.com.imageloadingdemo.network.datasource.NetworkState;
import imagecaching.ikram.com.imageloadingdemo.network.repository.CategoryRepositoryImp;
import retrofit2.Retrofit;

public class HomeViewModel extends ViewModel {
    private DataRepository dataRepository;
    public MutableLiveData<PagedList<Photo>> photoLiveData = new MutableLiveData<>();

    @Inject
    public HomeViewModel() {

    }

    public void requestPaging(String searchQuery,String galleryId) {
        dataRepository = new CategoryRepositoryImp(new JSONObject(),searchQuery, 10, galleryId);
        //dataRepository = new NotificationRepositoryImp(new JSONObject());

    }

    public MutableLiveData<PagedList<Photo>> getPhotoLiveData() {
        return photoLiveData;
    }

    public void setPhotoLiveData(MutableLiveData<PagedList<Photo>> photoLiveData) {
        this.photoLiveData = photoLiveData;
    }

    public LiveData<PagedList<Photo>> getGallery() {
        return dataRepository.getDataList();
    }

    public LiveData<NetworkState> dataLoadStatus() {
        return dataRepository.getDataLoadStatus();
    }

}
