package imagecaching.ikram.com.imageloadingdemo.network.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import imagecaching.ikram.com.imageloadingdemo.datamodels.Photo;
import imagecaching.ikram.com.imageloadingdemo.network.ApiClient;
import imagecaching.ikram.com.imageloadingdemo.network.datasource.DataFactory;
import imagecaching.ikram.com.imageloadingdemo.network.datasource.DataRepository;
import imagecaching.ikram.com.imageloadingdemo.network.datasource.NetworkState;
import imagecaching.ikram.com.imageloadingdemo.network.datasource.PagedKeyDataSourcePage;
import imagecaching.ikram.com.imageloadingdemo.network.datasource.ServiceManager;
import imagecaching.ikram.com.imageloadingdemo.network.domain.CategoryResponse;
import imagecaching.ikram.com.imageloadingdemo.network.endpoints.HomeService;
import imagecaching.ikram.com.imageloadingdemo.network.utils.AppsExecutor;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.arch.lifecycle.Transformations.switchMap;

public class CategoryRepositoryImp implements DataRepository {
    private static final String TAG = CategoryRepositoryImp.class.getSimpleName();
    private LiveData<PagedList<Photo>> itemList = new MutableLiveData<PagedList<Photo>>();
    private DataFactory dataSourceFactory;
    private int PAGE_SIZE = 10;
    private int totalPages;
    private String galleryId;

    public CategoryRepositoryImp(JSONObject inputJson, String searchQuery, int pageSize, String galId) {
        PAGE_SIZE = pageSize;
        galleryId = galId;
        dataSourceFactory = new DataFactory<Photo, PagedKeyDataSourcePage>(new PagedKeyDataSourcePage<Photo>(new ServiceManager<Photo, CategoryResponse>() {
            private Response<CategoryResponse> response = null;

            @Override
            public Response<CategoryResponse> callServiceMethod(JSONObject inputJson) throws Throwable {

                return response;

            }

            @Override
            public Response<CategoryResponse> callServiceMethod(int page, int perPage) throws Throwable {
                //response = ApiClient.getClient().create(HomeService.class).galleryData("flickr.galleries.getPhotos","5f738552fad95c2e86ba4a891cc593c0","json",
                response = ApiClient.getClient(searchQuery.length()>0).create(HomeService.class).galleryData("flickr.photos.search", "5f738552fad95c2e86ba4a891cc593c0", "json",
                        1, page, perPage, galleryId,searchQuery).execute();

                return response;
            }

            @Override
            public List<Photo> getDataList() {
                return response.body().getPhotos().getPhoto();
            }

            @Override
            public Response<CategoryResponse> getResponse() {
                if (totalPages == 0) {
                    totalPages = response.body().getPhotos().getPages();
                }
                return response;
            }

            @Override
            public int getStatus() {
                return response.body().getCode();
            }

            @Override
            public int getTotalPage() {
                return totalPages;
            }

            @Override
            public String getMessage() {
                return response.body().getMessage();
            }
        }, inputJson));
    }

    @Override
    public LiveData<PagedList<Photo>> getDataList() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(false)
                .build();
        itemList = new LivePagedListBuilder(dataSourceFactory, config)
                .setInitialLoadKey(0)
                .setFetchExecutor(AppsExecutor.networkIO())
                .build();
        return itemList;
    }

    @Override
    public LiveData<NetworkState> getDataLoadStatus() {
        return switchMap(dataSourceFactory.datasourceLiveData,
                dataSource -> ((PagedKeyDataSourcePage<Photo>) dataSource).networkStateMutableLiveData);
    }
}
