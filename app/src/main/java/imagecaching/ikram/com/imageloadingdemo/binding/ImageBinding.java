package imagecaching.ikram.com.imageloadingdemo.binding;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import imagecaching.ikram.com.imageloadingdemo.utils.AppPreferences;

import static imagecaching.ikram.com.imageloadingdemo.utils.AppPreferences.Keys.DEVICE_SCREEN_WIDTH;

/**
 * Created by ikram on 24/03/2019.
 */

public class ImageBinding {

    /**
     * @param imageView   imageview instance
     * @param imageType   0=>banner, 1=>profile picture,2=> certified image
     * @param imageUrl    url of image
     * @param placeHolder placeholder drawable id
     **/
    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"imageUrl", "placeHolder", "imageType"}, requireAll = true)
    public static void setImage(ImageView imageView, String imageUrl, int placeHolder, int imageType) {
        if (TextUtils.isEmpty(imageUrl))
            imageUrl = "abc";
        switch (imageType) {
            case 0:
                Picasso.with(imageView.getContext()).load(imageUrl).error(placeHolder).resize(AppPreferences.getInstance().getInt(DEVICE_SCREEN_WIDTH)/2, 0).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }

                    @Override
                    public void onError() {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                    }
                });
                break;
            case 1:

                Picasso.with(imageView.getContext()).load(imageUrl).error(placeHolder).resize(AppPreferences.getInstance().getInt(DEVICE_SCREEN_WIDTH), 0).into(imageView);
                break;
            default:
                Picasso.with(imageView.getContext()).load(imageUrl).error(placeHolder).into(imageView);
                break;
        }

    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"profileImageUrl"},requireAll = true)
    public static void setProfileImage(ImageView imageView, String imageUrl) {
        if (imageUrl == null || imageUrl.equals(""))
            return;
        Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
    }
    }
