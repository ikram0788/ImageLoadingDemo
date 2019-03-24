package imagecaching.ikram.com.imageloadingdemo.adapters;

import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import imagecaching.ikram.com.imageloadingdemo.BR;
import imagecaching.ikram.com.imageloadingdemo.R;
import imagecaching.ikram.com.imageloadingdemo.binder.ItemBinder;

public class PagedRecyclerAdapter<T> extends PagedListAdapter<T, PagedRecyclerAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private static final int ITEM_MODEL = -124;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private ItemBinder<T> itemBinder;
    private LayoutInflater inflater;
    private ClickHandler<T> clickHandler;
    private RecyclerChieldClickHandler<T> chieldClickHandler;
    private LongClickHandler<T> longClickHandler;
    private boolean isHeaderEnabled;
    private boolean isFooterEnabled = false;
    private T headerModel;

    protected PagedRecyclerAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(diffCallback);
    }

    protected PagedRecyclerAdapter(@NonNull AsyncDifferConfig<T> config) {
        super(config);
    }

    public PagedRecyclerAdapter(ItemBinder itemBinder, @Nullable PagedList items, @NonNull DiffUtil.ItemCallback<T> diffCallback, T photo) {
        super(diffCallback);
        this.itemBinder = itemBinder;
        this.headerModel = photo;
        submitList(items);
    }


    @NonNull
    @Override
    public PagedRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        ViewDataBinding binding = null;
        switch (viewType) {
            case TYPE_HEADER:

                binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.layout_header, null, false);
                break;
            case TYPE_FOOTER:
                binding = DataBindingUtil.inflate(inflater, viewType, viewGroup, false);
                break;
            default:
                binding = DataBindingUtil.inflate(inflater, viewType, viewGroup, false);
                break;
        }
        return new PagedRecyclerAdapter.ViewHolder(binding, viewType);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Nullable
    @Override
    protected T getItem(int position) {
        if(isPositionHeader(position) && headerModel!=null)
            return headerModel;
        return super.getItem(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        //final Guide item = items.get(position);
        if (getItemViewType(position) == TYPE_HEADER) {
            StaggeredGridLayoutManager.LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setFullSpan(true);
            viewHolder.itemView.setLayoutParams(layoutParams);
            viewHolder.binding.setVariable(BR.photo, getItem(position));

        } else if (getItemViewType(position) == TYPE_FOOTER) {
        } else {
            final T item = getItem(position);
            viewHolder.binding.setVariable(itemBinder.getBindingVariable(item), item);
            if (chieldClickHandler != null) {
                viewHolder.binding.setVariable(BR.position, position);
                viewHolder.binding.setVariable(BR.chieldClickHandler, chieldClickHandler);
            }

            viewHolder.binding.getRoot().setTag(ITEM_MODEL, item);
            viewHolder.binding.getRoot().setOnClickListener(this);
            viewHolder.binding.getRoot().setOnLongClickListener(this);

            viewHolder.binding.executePendingBindings();
        }
    }

    @Override
    public void onClick(View v) {
        if (clickHandler != null) {
            T item = (T) v.getTag(ITEM_MODEL);
            clickHandler.onClick(item, v);
        }
    }


    @Override
    public boolean onLongClick(View v) {
        if (longClickHandler != null) {
            T item = (T) v.getTag(ITEM_MODEL);
            longClickHandler.onLongClick(item);
            return true;
        }
        return false;
    }

    public void setClickHandler(ClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    public void setLongClickHandler(LongClickHandler clickHandler) {
        this.longClickHandler = clickHandler;
    }

    public void setChieldClickHandler(RecyclerChieldClickHandler chieldClickHandler) {
        this.chieldClickHandler = chieldClickHandler;
    }

    @Override
    public int getItemViewType(int position) {
        try {
            if (isPositionHeader(position)) {
                return TYPE_HEADER;

            } else if (isFooterEnabled) {
                return TYPE_FOOTER;
            }
            return itemBinder.getLayoutRes(getItem(position));
            //return TYPE_ITEM;

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return itemBinder.getLayoutRes(getItem(position));
    }

    private boolean isPositionHeader(int position) {
        return position == 0 && isHeaderEnabled;
    }

    public void setHeaderEnabled(boolean enabled) {
        isHeaderEnabled = enabled;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ViewDataBinding binding;

        ViewHolder(ViewDataBinding binding, int viewType) {
            super(binding.getRoot());
            switch (viewType) {
                case TYPE_HEADER:
                    this.binding = binding;
                    break;
                case TYPE_FOOTER:
                    this.binding = binding;
                    break;
                default:
                    this.binding = binding;
                    break;
            }
        }
    }

    public void setPagedList(PagedList<T> pagedList) {
        submitList(pagedList);
    }

}
