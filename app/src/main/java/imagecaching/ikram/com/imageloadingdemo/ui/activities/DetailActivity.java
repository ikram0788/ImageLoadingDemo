package imagecaching.ikram.com.imageloadingdemo.ui.activities;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.transition.TransitionInflater;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import dagger.android.AndroidInjection;
import imagecaching.ikram.com.imageloadingdemo.BR;
import imagecaching.ikram.com.imageloadingdemo.R;
import imagecaching.ikram.com.imageloadingdemo.databinding.ActivityDetailBinding;
import imagecaching.ikram.com.imageloadingdemo.viewmodels.DetailViewModel;
import imagecaching.ikram.com.imageloadingdemo.viewmodels.SplashViewModel;

import static imagecaching.ikram.com.imageloadingdemo.utils.Constants.EXTRA_POSITION;

public class DetailActivity extends BaseActivity<DetailViewModel, ActivityDetailBinding> {
    //private SplashViewModel viewModel;
    @Override
    public int getLayoutRes() {
        return R.layout.activity_detail;
    }

    @Override
    public Class<DetailViewModel> getViewModel() {
        return DetailViewModel.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        //viewModel = ViewModelProviders.of(this).get(getViewModel());
//        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
//        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
//        dataBinding.setVariable(BR.viewModel, viewModel);
        dataBinding.setCategoryType(getIntent().getStringExtra("DATA_TYPE"));
        dataBinding.setFragmentManager(getSupportFragmentManager());
        //setupActionBar();
        viewModel.setPhotoLiveData(getIntent().getParcelableArrayListExtra("DATA_LIST"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                dataBinding.viewPagerDetail.setCurrentItem(getIntent().getIntExtra("POSITION", 0));
            }
        }, 10);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
            dataBinding.viewPagerDetail.setTransitionName("iv_gallery");
        }
    }

    private void setResult() {
        int position = dataBinding.viewPagerDetail.getCurrentItem();
        Intent data = new Intent();
        data.putExtra(EXTRA_POSITION, position);
        setResult(RESULT_OK, data);
    }
    @Override
    public void onBackPressed() {
        setResult();
        super.onBackPressed();
        //overridePendingTransition(R.anim.stay_no_anim, R.anim.slid_down);
    }

    /*private void setupActionBar() {

            Toolbar toolbar = dataBinding.includeToolbar.toolbar;
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            dataBinding.includeToolbar.toolbar.setTitle("Demo");
        }*/
}
