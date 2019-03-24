package imagecaching.ikram.com.imageloadingdemo.binding;

import android.arch.lifecycle.LiveData;
import android.databinding.BindingAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import java.util.List;

import imagecaching.ikram.com.imageloadingdemo.adapters.CategoryPagerAdapter;
import imagecaching.ikram.com.imageloadingdemo.datamodels.Photo;

/**
 * Created by ikram on 24/03/2019.
 */

public class ViewPagerBindings {
    private static final int KEY_VIEW_ITEMS = -223;
    private static final int KEY_VIEW_CLICK_HANDLER = -224;
    private static final int KEY_VIEW_LONG_CLICK_HANDLER = -225;
    private static final int KEY_PAGE_INDECATOR = -226;
    private static final int KEY_FRAGMENT_MANAGER = -227;
    private static final int KEY_ADAPTER_TYPE = -228;
    private static final int KEY_TAB_LAYOUT = -229;
    private static final int KEY_ITEM_POSITION = -230;


    @SuppressWarnings("unchecked")
    @BindingAdapter("viewPagerFM")
    public static <T> void setViewPagerFM(ViewPager viewPager, FragmentManager fragmentManager) {

        //FragmentManager fragmentManager = (FragmentManager) viewPager.getTag(KEY_FRAGMENT_MANAGER);
        String adapterType = (String) viewPager.getTag(KEY_ADAPTER_TYPE);
        int position = (Integer) viewPager.getTag(KEY_ITEM_POSITION);
        List<Photo> photoList = (List<Photo>) viewPager.getTag(KEY_VIEW_ITEMS);
        if (fragmentManager != null) {
            CategoryPagerAdapter categoryPagerAdapter = new CategoryPagerAdapter(fragmentManager, adapterType, position);

            if (photoList != null)
                categoryPagerAdapter.setPhotoList(photoList);
            viewPager.setAdapter(categoryPagerAdapter);

        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("adapterType")
    public static <T> void setAdapterTypeType(ViewPager viewPager, String adapterType) {

        viewPager.setTag(KEY_ADAPTER_TYPE, adapterType);
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("adapterItemPosition")
    public static <T> void setSelectedPosition(ViewPager viewPager, int position) {
        CategoryPagerAdapter pagerAdapter = (CategoryPagerAdapter) viewPager.getAdapter();
        if (pagerAdapter == null)
            viewPager.setTag(KEY_ITEM_POSITION, position);
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("adapterItems")
    public static <T> void setAdapterItems(ViewPager viewPager, LiveData<List<Photo>> listLiveData) {
        CategoryPagerAdapter pagerAdapter = (CategoryPagerAdapter) viewPager.getAdapter();
        if (pagerAdapter == null)
            viewPager.setTag(KEY_VIEW_ITEMS, listLiveData.getValue());
        else
            pagerAdapter.setPhotoList(listLiveData.getValue());
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("viewPagerItemLayout")
    public static <T> void setViewPagerTAB(ViewPager viewPager, LinearLayout tabLayout) {

        viewPager.setTag(KEY_TAB_LAYOUT, tabLayout);
    }
}
