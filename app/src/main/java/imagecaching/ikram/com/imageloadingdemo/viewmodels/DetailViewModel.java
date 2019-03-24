package imagecaching.ikram.com.imageloadingdemo.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import imagecaching.ikram.com.imageloadingdemo.datamodels.Photo;
import imagecaching.ikram.com.imageloadingdemo.network.datasource.DataRepository;

public class DetailViewModel extends ViewModel {
    private DataRepository dataRepository;
    //public LiveData<Resource1<List<Notification>>> photoListLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Photo>> photoLiveData = new MutableLiveData<>();
    @Inject
    public DetailViewModel() {
    }

    public MutableLiveData<List<Photo>> getPhotoLiveData() {
        return photoLiveData;
    }

    public void setPhotoLiveData(List<Photo> photoLiveData) {
        this.photoLiveData.setValue(photoLiveData);
    }
}
