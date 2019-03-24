package imagecaching.ikram.com.imageloadingdemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import imagecaching.ikram.com.imageloadingdemo.DemoApplication;

public class Utils {
    public static void shotToast(String message){
        Toast.makeText(DemoApplication.getInstance(),message,Toast.LENGTH_SHORT).show();
    }

    public static boolean hasNetwork(Context context){
        boolean isConnected= false; // Initial Value
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected())
            isConnected = true;
        return isConnected;
    }
}
