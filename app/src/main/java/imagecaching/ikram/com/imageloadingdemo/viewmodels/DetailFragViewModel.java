package imagecaching.ikram.com.imageloadingdemo.viewmodels;

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

public class DetailFragViewModel extends ViewModel {
    public MutableLiveData<PagedList<Photo>> photoListLiveData = new MutableLiveData<>();
    public MutableLiveData<Photo> photoLiveData = new MutableLiveData<>();


    @Inject
    DetailFragViewModel() {
    }


    public MutableLiveData<Photo> getPhotoLiveData() {
        return photoLiveData;
    }

    public void setPhotoLiveData(Photo photo) {
        this.photoLiveData.setValue(photo);
    }

    public MutableLiveData<PagedList<Photo>> getPhotoListLiveData() {
        return photoListLiveData;
    }

    public void setPhotoListLiveData(MutableLiveData<PagedList<Photo>> photoListLiveData) {
        this.photoListLiveData = photoListLiveData;
    }

}
