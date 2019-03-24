package imagecaching.ikram.com.imageloadingdemo.utils;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Constants {
    // Picasso cache size (500 MB)
    static int MAX_CACHE_SIZE=1024*1024*500;
    static String EXTRA_POSITION="EXTRA_POSITION";
    static int REQUEST_DETAIL=100;
    // Declare the @IntDef for these constants
    @StringDef({PageTitle.HomeActivity, PageTitle.CategoryFragment, PageTitle.Photographers,
            PageTitle.MakeupArtists, PageTitle.Decorators, PageTitle.BridalDesigners,
            PageTitle.MehndiArtists
    })
    @Retention(RetentionPolicy.SOURCE)
    @interface PageTitle {
        String HomeActivity = "Home";
        String CategoryFragment = " Category ";
        String Photographers = "Photographers";
        String MakeupArtists = "Makeup Artists";
        String Decorators = "Decorators";
        String BridalDesigners = "Bridal Designers";
        String MehndiArtists = "Mehndi Artists";
    }
}
