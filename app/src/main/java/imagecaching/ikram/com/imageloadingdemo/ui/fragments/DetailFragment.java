package imagecaching.ikram.com.imageloadingdemo.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import imagecaching.ikram.com.imageloadingdemo.R;
import imagecaching.ikram.com.imageloadingdemo.databinding.FragmentDetailBinding;
import imagecaching.ikram.com.imageloadingdemo.datamodels.Photo;
import imagecaching.ikram.com.imageloadingdemo.viewmodels.DetailFragViewModel;

/**
 * Created by ikram on 24/03/2019.
 * A simple {@link BaseFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends BaseFragment<DetailFragViewModel, FragmentDetailBinding> {
    private static final String ARG_TITLE = "Detail page";
    private static final String ARG_DATA = "ARG_DATA";

    private String mPageTitle;
    private String TAG = DetailFragment.class.getSimpleName();

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment OrderDetailFragment.
     */
    public static DetailFragment newInstance(String title, Photo photo) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putParcelable(ARG_DATA, photo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPageTitle = getArguments().getString(ARG_TITLE);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void setObserver() {
        viewModelObject.photoLiveData.observe(getViewLifecycleOwner(), photo -> {
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //dataBinding.setChieldClickHandler(chieldClickHandler());
        //dataBinding.setItemBinder(itemViewBinder());
        viewModelObject.setPhotoLiveData(getArguments().getParcelable(ARG_DATA));
        //viewModelObject.requestPaging(getArguments().getString(ARG_TITLE));

        setObserver();
    }

    @Override
    public Class<DetailFragViewModel> getViewModel() {
        return DetailFragViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_detail;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
