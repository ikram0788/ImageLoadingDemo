package imagecaching.ikram.com.imageloadingdemo.di;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import imagecaching.ikram.com.imageloadingdemo.network.utils.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ikram on 24/03/2019.
 */
@Module(includes = ViewModelModule.class)
public class AppModule {

        @Provides
        @Singleton
        OkHttpClient provideOkHttpClient() {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
            okHttpClient.connectTimeout(Constants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
            okHttpClient.readTimeout(Constants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
            okHttpClient.addInterceptor(logging);
            return okHttpClient.build();
        }

        @Provides
        @Singleton
        Retrofit provideRetrofit(OkHttpClient okHttpClient) {
            return new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
   /* @Provides
    @Singleton
    GuidetApplication getGuidetApplication(GuidetApplication application) {
        return application;
    }
    */

/*
    @Provides
    @Singleton
    AppDatabase getDatabase(Application application, AppExecutors executors) {
        return AppDatabase.getInstance(application, executors);
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }
    @Provides
    @Singleton
    AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }
    */
}
