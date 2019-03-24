package imagecaching.ikram.com.imageloadingdemo.network;

import java.util.concurrent.TimeUnit;

import imagecaching.ikram.com.imageloadingdemo.DemoApplication;
import imagecaching.ikram.com.imageloadingdemo.network.utils.Constants;
import imagecaching.ikram.com.imageloadingdemo.utils.Utils;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ikram on 24/03/2019.
 */

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(boolean cacheClient) {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            // Basic auth
            /*Interceptor basicAouth = chain -> {
                String credentials = Credentials.basic(USER_NAME, PASSWORD);
                Request request = chain.request();
                Request authenticatedRequest = request.newBuilder()
                        .header("Authorization", credentials).build();
                return chain.proceed(authenticatedRequest);
            }*/
            long cacheSize = (15 * 1024 * 1024);
            Cache myCache = new Cache(DemoApplication.getInstance().getCacheDir(), cacheSize);
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .cache(myCache)
                    .addInterceptor(chain -> {
                                Request request = chain.request();
                                if (Utils.hasNetwork(DemoApplication.getInstance())) {
                                    request.newBuilder().header("Cache-Control", "public, max-age=" + 1).build();
                                } /*else if(cacheClient){
                                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                                }*/ else {
                                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                                }
                                return chain.proceed(request);
                            }
                    )
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
