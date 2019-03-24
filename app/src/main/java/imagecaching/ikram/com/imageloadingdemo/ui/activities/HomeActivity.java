package imagecaching.ikram.com.imageloadingdemo.ui.activities;

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;

import imagecaching.ikram.com.imageloadingdemo.BR;
import imagecaching.ikram.com.imageloadingdemo.R;
import imagecaching.ikram.com.imageloadingdemo.adapters.ClickHandler;
import imagecaching.ikram.com.imageloadingdemo.adapters.PagedRecyclerAdapter;
import imagecaching.ikram.com.imageloadingdemo.adapters.RecyclerChieldClickHandler;
import imagecaching.ikram.com.imageloadingdemo.binder.CompositeItemBinder;
import imagecaching.ikram.com.imageloadingdemo.binder.ConditionalDataBinder;
import imagecaching.ikram.com.imageloadingdemo.binder.ItemBinder;
import imagecaching.ikram.com.imageloadingdemo.databinding.ActivityHomeBinding;
import imagecaching.ikram.com.imageloadingdemo.datamodels.Photo;
import imagecaching.ikram.com.imageloadingdemo.utils.ItemOffsetDecoration;
import imagecaching.ikram.com.imageloadingdemo.viewmodels.HomeViewModel;

import static imagecaching.ikram.com.imageloadingdemo.utils.Constants.REQUEST_DETAIL;

public class HomeActivity extends BaseActivity<HomeViewModel, ActivityHomeBinding> {
    @Override
    public int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    public Class<HomeViewModel> getViewModel() {
        return HomeViewModel.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        dataBinding.rvCategoryList.setLayoutManager(new GridLayoutManager(this, 2));
        dataBinding.rvCategoryList.addItemDecoration(new ItemOffsetDecoration(this, R.dimen.dimen_grid_space));
        //dataBinding.setClickHandler(clickHandler());
        dataBinding.setChieldClickHandler(chieldClickHandler());
        dataBinding.setItemBinder(itemViewBinder());
        viewModel.requestPaging("", "decorators");
        dataBinding.setViewModel(viewModel);
        setObserver();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        MenuItem search_item = menu.findItem(R.id.menu_search);

        SearchView searchView = (SearchView) search_item.getActionView();
        searchView.setFocusable(false);
        searchView.setQueryHint("Search");
        //ImageView closeButton = (ImageView)searchView.findViewById(R.id.search_close_btn);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String s) {
                ((PagedRecyclerAdapter) dataBinding.rvCategoryList.getAdapter()).submitList(null);
                viewModel.requestPaging(s, "decorators");
                setObserver();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }

        });
        searchView.setOnCloseListener(() -> {
            ((PagedRecyclerAdapter) dataBinding.rvCategoryList.getAdapter()).submitList(null);
            viewModel.requestPaging("", "decorators");
            setObserver();
            return false;
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_span1:
                dataBinding.rvCategoryList.setLayoutManager(new GridLayoutManager(this, 1));
                break;
            case R.id.action_span2:
                dataBinding.rvCategoryList.setLayoutManager(new GridLayoutManager(this, 2));
                break;
            case R.id.action_span3:
                dataBinding.rvCategoryList.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            case R.id.action_span4:
                dataBinding.rvCategoryList.setLayoutManager(new GridLayoutManager(this, 4));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupActionBar() {

        Toolbar toolbar = dataBinding.includeToolbar.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        dataBinding.includeToolbar.toolbar.setTitle("Image Loading");
    }

    private void setObserver() {
        viewModel.dataLoadStatus().observe(this, status -> {
        });
        viewModel.getGallery().observe(this, resource -> {
            viewModel.photoLiveData.setValue(resource);
            ((PagedRecyclerAdapter) dataBinding.rvCategoryList.getAdapter()).submitList(resource);
            Log.v("RESULT", resource.toString());
        });

    }

    public <T> ItemBinder<T> itemViewBinder() {
        return new CompositeItemBinder<T>(new ConditionalDataBinder<T>(BR.entity,
                R.layout.item_gallery_list) {
            @Override
            public boolean canHandle(T model) {
                return true;
            }
        });

    }

    public <T> ClickHandler<T> clickHandler() {
        return (viewModel, v) -> {
        };
    }

    public <T> RecyclerChieldClickHandler<T> chieldClickHandler() {
        return (model, view, position) -> {
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "iv_gallery");
            Intent detailIntent = new Intent(HomeActivity.this, DetailActivity.class);
            detailIntent.putParcelableArrayListExtra("DATA_LIST", new ArrayList<>(viewModel.photoLiveData.getValue().subList(0, viewModel.photoLiveData.getValue().size() - 1)));
            detailIntent.putExtra("POSITION", position);
            detailIntent.putExtra("DATA_TYPE", "");
            //startActivityForResult(detailIntent,REQUEST_DETAIL,options.toBundle());
            startActivity(detailIntent, options.toBundle());

        };
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        final int position = data.getIntExtra("EXTRA_POSITION", 0);
        if (position != RecyclerView.NO_POSITION) {
            dataBinding.rvCategoryList.scrollToPosition(position);
        }
    }

    public <T> DiffUtil.ItemCallback<T> getDiffUtilItemCallback() {
        return new DiffUtil.ItemCallback<T>() {

            @Override
            public boolean areItemsTheSame(T oldItem, T newItem) {
                if (oldItem instanceof Photo)
                    return ((Photo) oldItem).getId().equals(((Photo) newItem).getId());
                return false;
            }

            @Override
            public boolean areContentsTheSame(T oldItem, T newItem) {
                return oldItem.equals(newItem);
            }
/*
            @Nullable
            @Override
            public Object getChangePayload(@NonNull T oldItem, @NonNull T newItem) {
                if (!oldItem.equals(newItem)) {
                    return Boolean.FALSE;
                } else {
                    return null;
                }
            }*/
        };
    }
}
