package imagecaching.ikram.com.imageloadingdemo.network.endpoints;


import imagecaching.ikram.com.imageloadingdemo.network.domain.CategoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by ikram on 24/03/2019.
 */

public interface HomeService {
    /**
     * @param page gallery page index
     * @param perPage per page items
     * @param galleryId ID of requested gallery
     *
     **/
    @Headers("Content-Type: application/json")
    @GET("rest/")
    Call<CategoryResponse> galleryData(@Query("method") String method, @Query("api_key") String api_key, @Query("format") String format, @Query("nojsoncallback") int nojsoncallback,
                                       @Query("page") int page, @Query("per_page") int perPage, @Query("tags") String galleryId, @Query("text") String searchQuery);
}
