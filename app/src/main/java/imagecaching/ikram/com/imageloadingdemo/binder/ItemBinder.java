package imagecaching.ikram.com.imageloadingdemo.binder;

/**
 * Created by ikram on 24/03/2019.
 */

public interface ItemBinder<T> {
    int getLayoutRes(T model);

    int getBindingVariable(T model);
}
