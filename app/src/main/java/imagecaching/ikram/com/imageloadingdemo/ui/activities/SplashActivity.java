package imagecaching.ikram.com.imageloadingdemo.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;

import dagger.android.AndroidInjection;
import imagecaching.ikram.com.imageloadingdemo.BR;
import imagecaching.ikram.com.imageloadingdemo.R;
import imagecaching.ikram.com.imageloadingdemo.databinding.ActivitySplashBinding;
import imagecaching.ikram.com.imageloadingdemo.utils.AppPreferences;
import imagecaching.ikram.com.imageloadingdemo.viewmodels.SplashViewModel;

public class SplashActivity extends BaseActivity<SplashViewModel, ActivitySplashBinding> {

    private final int SPLASH_TIME_OUT = 3000;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    public Class<SplashViewModel> getViewModel() {
        return SplashViewModel.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        //viewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
//        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
        //dataBinding.setVariable(BR.viewModel, viewModel);

        new Handler().postDelayed(() -> {

            if (AppPreferences.getInstance().getInt(AppPreferences.Keys.DEVICE_SCREEN_WIDTH) == 0) {
                Display display = getWindowManager().getDefaultDisplay();
                DisplayMetrics outMetrics = new DisplayMetrics();
                display.getMetrics(outMetrics);

                //float density = getResources().getDisplayMetrics().density;
                //float dpHeight = outMetrics.heightPixels/ density;
                //float dpWidth  = outMetrics.widthPixels / density;
                float height = outMetrics.heightPixels;
                float width = outMetrics.widthPixels;
                AppPreferences.getInstance().setInt(AppPreferences.Keys.DEVICE_SCREEN_HEIGHT, (int) height);
                AppPreferences.getInstance().setInt(AppPreferences.Keys.DEVICE_SCREEN_WIDTH, (int) width);

            }
            Intent i = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(i);

            // close this activity
            finish();
        }, SPLASH_TIME_OUT);
    }
}
