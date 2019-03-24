package imagecaching.ikram.com.imageloadingdemo.adapters;

import android.view.View;

/**
 * Created by ikram on 24/03/2019.
 */

public interface RecyclerChieldClickHandler<T> {
    void onClick(T viewModel, View v, int position);
}
