package imagecaching.ikram.com.imageloadingdemo.network.utils;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Constants {
    int TIMEOUT_IN_SEC=30;
    String BASE_URL="https://api.flickr.com/services/";
    String OWNER_DETAIL_URL="https://api.flickr.com/services/";


    @IntDef({ContentState.CONTENT,ContentState.LOADING,
            ContentState.ERROR_NETWORK,ContentState.ERROR_GENERAL
    })
    @Retention(RetentionPolicy.SOURCE)
    @interface ContentState {
        int CONTENT = 00;
        int LOADING = 01;
        int ERROR_NETWORK = 02;
        int ERROR_GENERAL = 03;
    }

    @StringDef({RequestType.OWNER_DETAIL_REQUEST
    })
    @Retention(RetentionPolicy.SOURCE)
    @interface RequestType {
        String OWNER_DETAIL_REQUEST = OWNER_DETAIL_URL;
    }
}
