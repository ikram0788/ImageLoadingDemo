package imagecaching.ikram.com.imageloadingdemo.binding;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.databinding.BindingAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import imagecaching.ikram.com.imageloadingdemo.adapters.ClickHandler;
import imagecaching.ikram.com.imageloadingdemo.adapters.LongClickHandler;
import imagecaching.ikram.com.imageloadingdemo.adapters.PagedRecyclerAdapter;
import imagecaching.ikram.com.imageloadingdemo.adapters.RecyclerChieldClickHandler;
import imagecaching.ikram.com.imageloadingdemo.binder.ItemBinder;
import imagecaching.ikram.com.imageloadingdemo.datamodels.Photo;

/**
 * Created by ikram on 24/03/2019.
 */


public class RecyclerPagedViewBindings {
    private static final int KEY_HEADER = -122;
    private static final int KEY_ITEMS = -123;
    private static final int KEY_CLICK_HANDLER = -124;
    private static final int KEY_LONG_CLICK_HANDLER = -125;
    private static final int KEY_CHIELD_CLICK_HANDLER = -126;
    private static final int KEY_FOOTER = -127;
    private static final int KEY_DIFF_CALLBACK = -129;
    private static final int KEY_LAYOUT_MANAGER = -130;
    private static final int KEY_HEADER_MODEL = -131;


    @SuppressWarnings("unchecked")
    @BindingAdapter("pagedItems")
    public static <T> void setPagedList(RecyclerView recyclerView, LiveData<PagedList<T>> items) {
        if (items == null)
            return;
        PagedRecyclerAdapter adapter = (PagedRecyclerAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setPagedList(items.getValue());
        } else {
            recyclerView.setTag(KEY_ITEMS, items.getValue());
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("pagedDiffCallback")
    public static <T> void setDiffCallback(RecyclerView recyclerView, DiffUtil.ItemCallback<T> diffCallback) {
        //PagedRecyclerAdapter adapter = (PagedRecyclerAdapter) recyclerView.getAdapter();
        recyclerView.setTag(KEY_DIFF_CALLBACK, diffCallback);
    }
    @SuppressWarnings("unchecked")
    @BindingAdapter("pagedHeader")
    public static <T> void setHeader(RecyclerView recyclerView, LiveData<Photo> headerModel) {
        recyclerView.setTag(KEY_HEADER_MODEL, headerModel.getValue());
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("pagedClickHandler")
    public static <T> void setHandler(RecyclerView recyclerView, ClickHandler<T> handler) {
        PagedRecyclerAdapter<T> adapter = (PagedRecyclerAdapter<T>) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setClickHandler(handler);
        } else {
            recyclerView.setTag(KEY_CLICK_HANDLER, handler);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("pagedLongClickHandler")
    public static <T> void setHandler(RecyclerView recyclerView, LongClickHandler<T> handler) {
        PagedRecyclerAdapter adapter = (PagedRecyclerAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setLongClickHandler(handler);
        } else {
            recyclerView.setTag(KEY_LONG_CLICK_HANDLER, handler);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("pagedChieldClickHandler")
    public static <T> void setHandler(RecyclerView recyclerView, RecyclerChieldClickHandler<T> chieldClickHandler) {
        PagedRecyclerAdapter adapter = (PagedRecyclerAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setChieldClickHandler(chieldClickHandler);
        } else {
            recyclerView.setTag(KEY_CHIELD_CLICK_HANDLER, chieldClickHandler);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("pagedItemViewBinder")
    public static <T> void setItemViewBinder(RecyclerView recyclerView, ItemBinder<T> itemViewMapper) {
        PagedList<T> items = (PagedList<T>) recyclerView.getTag(KEY_ITEMS);
        ClickHandler<T> clickHandler = (ClickHandler<T>) recyclerView.getTag(KEY_CLICK_HANDLER);
        Photo entity = (Photo) recyclerView.getTag(KEY_HEADER_MODEL);
        DiffUtil.ItemCallback<T> diffCallback = (DiffUtil.ItemCallback<T>) recyclerView.getTag(KEY_DIFF_CALLBACK);
        RecyclerChieldClickHandler<T> chieldClickHandler = (RecyclerChieldClickHandler<T>) recyclerView.getTag(KEY_CHIELD_CLICK_HANDLER);
        PagedRecyclerAdapter adapter = new PagedRecyclerAdapter(itemViewMapper, items, diffCallback,entity);

        if (clickHandler != null) {
            adapter.setClickHandler(clickHandler);
        }
        if (chieldClickHandler != null) {
            adapter.setChieldClickHandler(chieldClickHandler);
        }
        recyclerView.setAdapter(adapter);
    }

}