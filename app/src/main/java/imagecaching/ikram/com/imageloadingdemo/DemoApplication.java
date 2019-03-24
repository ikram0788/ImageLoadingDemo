package imagecaching.ikram.com.imageloadingdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import imagecaching.ikram.com.imageloadingdemo.di.DaggerAppComponent;
import imagecaching.ikram.com.imageloadingdemo.utils.AppPreferences;

import static imagecaching.ikram.com.imageloadingdemo.utils.Constants.MAX_CACHE_SIZE;

/**
 * Created by ikram on 24/03/2019.
 */

public class DemoApplication extends Application implements HasActivityInjector {
    private static DemoApplication instance;
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    public static synchronized DemoApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppPreferences.initialize(this);
        initializeComponent();

        Picasso picasso = new Picasso.Builder(this)
                .memoryCache(new LruCache(MAX_CACHE_SIZE))
                .build();
        instance = this;
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }

    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
