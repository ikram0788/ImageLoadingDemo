package imagecaching.ikram.com.imageloadingdemo.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import imagecaching.ikram.com.imageloadingdemo.ui.activities.BaseActivity;
import imagecaching.ikram.com.imageloadingdemo.ui.activities.DetailActivity;
import imagecaching.ikram.com.imageloadingdemo.ui.activities.HomeActivity;
import imagecaching.ikram.com.imageloadingdemo.ui.activities.SplashActivity;

/**
 * Created by ikram on 24/03/2019.
 */
@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract HomeActivity homeActivity();
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract DetailActivity detailActivity();
}
