package imagecaching.ikram.com.imageloadingdemo.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import imagecaching.ikram.com.imageloadingdemo.viewmodels.DetailFragViewModel;
import imagecaching.ikram.com.imageloadingdemo.viewmodels.DetailViewModel;
import imagecaching.ikram.com.imageloadingdemo.viewmodels.HomeViewModel;
import imagecaching.ikram.com.imageloadingdemo.viewmodels.SplashViewModel;

/**
 * Created by ikram on 24/03/2019.
 */

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    abstract ViewModel splashViewModel(SplashViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel homeViewModel(HomeViewModel homeViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    abstract ViewModel detailViewModel(DetailViewModel detailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailFragViewModel.class)
    abstract ViewModel detailFragViewModel(DetailFragViewModel detailFragViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
