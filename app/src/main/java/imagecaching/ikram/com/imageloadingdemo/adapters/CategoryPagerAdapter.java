package imagecaching.ikram.com.imageloadingdemo.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import imagecaching.ikram.com.imageloadingdemo.datamodels.Photo;
import imagecaching.ikram.com.imageloadingdemo.ui.fragments.BaseFragment;
import imagecaching.ikram.com.imageloadingdemo.ui.fragments.DetailFragment;

public class CategoryPagerAdapter extends FragmentStatePagerAdapter {
    public static String CATEGORY_TYPE = "CATEGORY";
    public static String CATEGORY_DETAIL_TYPE = "CATEGORY_DETAIL";
    private int position = 0;
    private List<Photo> mPhotoList = new ArrayList<>();
    private String mAdapterType = CATEGORY_TYPE; //CATEGORY=>Home view pager, CATEGORY_DETAIL=>Pic detail view pager

    public CategoryPagerAdapter(FragmentManager fm, String adapterType) {
        super(fm);
        mAdapterType = adapterType;
    }

    public CategoryPagerAdapter(FragmentManager fm, String adapterType, int position) {
        super(fm);
        mAdapterType = adapterType;
        this.position = position;

    }

    public void setPhotoList(List<Photo> photoList) {
        mPhotoList = photoList;
        notifyDataSetChanged();
    }

    @Override
    public BaseFragment getItem(int position) {
        if (!mAdapterType.equals(CATEGORY_TYPE)) {
            /*int initialPosition;
            if (this.position != 0) {
                initialPosition = this.position;
                this.position = 0;
                return DetailFragment.newInstance(mAdapterType, mPhotoList.get(initialPosition));
            }else*/
            return DetailFragment.newInstance(mAdapterType, mPhotoList.get(position));

        }
        return null;
    }

    @Override
    public int getCount() {
        return mPhotoList.size();
    }
}
