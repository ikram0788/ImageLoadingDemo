package imagecaching.ikram.com.imageloadingdemo.di;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import imagecaching.ikram.com.imageloadingdemo.ui.fragments.DetailFragment;

/**
 * Created by ikram on 24/03/2019.
 */
@Module
public abstract class FragmentBuilderModule {
/*
    @ContributesAndroidInjector
    abstract BaseFragment contributeBaseFragment();*/
    @ContributesAndroidInjector
    abstract DetailFragment detailFragment();
}
