package imagecaching.ikram.com.imageloadingdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Set;

/**
 * Created by ikram on 24/03/2019.
 */

public class AppPreferences {
    private static SharedPreferences preferences;
    private static AppPreferences instance;
    private SharedPreferences.OnSharedPreferenceChangeListener mSharedPreferenceChangeListener;

    public AppPreferences() {

    }

    public AppPreferences(Context appContext) {

        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
    }

    public static void initialize(Context appContext) {
        if (instance == null) {
            instance = new AppPreferences();
        }

        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
        //preferences.setSharedPreferencesMode(Context.MODE_MULTI_PROCESS);

    }

    public static AppPreferences getInstance() {
        return instance;
    }

    public void setListner(SharedPreferences.OnSharedPreferenceChangeListener listner) {
//        mSharedPreferenceChangeListener = listner;
//        instance.preferences.registerOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener);
        instance.preferences.registerOnSharedPreferenceChangeListener(listner);
    }

    public void setString(@Keys String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key.toString(), value);
        editor.commit();
    }

    public String getString(String key) {
        /*if (key == Keys.UUID)
            return preferences.getString(key.toString(), "1234567890");
        else if (key == Keys.REFER_CODE)
            return preferences.getString(key.toString(), "LAUNDRETTE");*/
        return preferences.getString(key.toString(), "");
    }

    public void setInt(@Keys String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key.toString(), value);
        editor.commit();
//        if (mSharedPreferenceChangeListener != null)
//            mSharedPreferenceChangeListener.onSharedPreferenceChanged(preferences, key);
    }

    public int getInt(@Keys String key) {
        return preferences.getInt(key.toString(), 0);
    }

    public void setLong(@Keys String key, long value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key.toString(), value);
        editor.commit();
    }

    public long getLong(@Keys String key) {
        return preferences.getLong(key.toString(), 0l);
    }

    public void setBoolean(@Keys String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key.toString(), value);
        editor.commit();
    }

    public boolean getBoolean(@Keys String key) {
        return preferences.getBoolean(key.toString(), false);
    }

    public void setFloat(@Keys String key, float value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key.toString(), value);
        editor.commit();
    }

    public Float getFloat(@Keys String key) {
        return preferences.getFloat(key.toString(), 0.0f);
    }

    public void setStringSet(@Keys String key, Set<String> value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(key.toString(), value);
        editor.commit();
    }

    public Set<String> getStringSet(@Keys String key) {
        return preferences.getStringSet(key.toString(), null);
    }


    // Declare the @IntDef for these constants
    @StringDef({Keys.DEVICE_SCREEN_WIDTH, Keys.DEVICE_SCREEN_HEIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Keys {
        String DEVICE_SCREEN_WIDTH = "DEVICE_SCREEN_WIDTH";
        String DEVICE_SCREEN_HEIGHT = "DEVICE_SCREEN_HEIGHT";
    }
}
