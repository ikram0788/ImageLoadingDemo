package imagecaching.ikram.com.imageloadingdemo.di;

import android.app.Application;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import imagecaching.ikram.com.imageloadingdemo.DemoApplication;
import imagecaching.ikram.com.imageloadingdemo.ui.activities.BaseActivity;

/**
 * Created by ikram on 24/03/2019.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        FragmentBuilderModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(DemoApplication aaApp);
    //void inject(BaseActivity baseActivity);

}
