package imagecaching.ikram.com.imageloadingdemo.ui.activities;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.MotionEvent;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import imagecaching.ikram.com.imageloadingdemo.BR;
import imagecaching.ikram.com.imageloadingdemo.R;
import imagecaching.ikram.com.imageloadingdemo.ui.fragments.BaseFragment;
import imagecaching.ikram.com.imageloadingdemo.utils.Constants;

/**
 * Created by ikram on 24/03/2019.
 */

public abstract class BaseActivity<VM extends ViewModel, DB extends ViewDataBinding> extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener, HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;
    private Handler mHandler;
    private ProgressDialog mProgressDialog;
    public VM viewModel;
    public DB dataBinding;

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract Class<VM> getViewModel();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);

        viewModel = ViewModelProviders.of(this).get(getViewModel());
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
        dataBinding.setVariable(BR.viewModel, viewModel);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Please wait");
        mProgressDialog.setMessage("Loading...");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorAccent));
        }

    }

    protected void startActivity(BaseActivity baseActivity, Bundle bundle, boolean finishCurrent) {
        Intent intent = new Intent(baseActivity, baseActivity.getClass());
        intent.putExtras(bundle);
        startActivity(intent);
        if (finishCurrent)
            baseActivity.finish();
    }

    @Override
    public void onFragmentInteraction(BaseFragment baseFragment, @Constants.PageTitle String fragmentTitle, Object data) {

    }

    @Override
    public void updateTitle(String pageTitle, String subTitle) {
    }

    @Override
    public void updateContent(int position, Object data) {

    }

    public void showProgress(boolean show) {
        if (show)
            mProgressDialog.show();
        else
            mProgressDialog.dismiss();


//        if (mProgressDialog.isShowing() && !show)
//            mProgressDialog.dismiss();
//        else
//            mProgressDialog.show();
    }

    public void setProgressCancelable(boolean cancelable) {
        mProgressDialog.setCancelable(cancelable);
    }

    protected void setupActionbar() {

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //Utils.hideSoftKeybord(this);
        return super.dispatchTouchEvent(ev);
    }

}
