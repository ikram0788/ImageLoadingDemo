package imagecaching.ikram.com.imageloadingdemo.binder;

/**
 * Created by ikram on 24/03/2019.
 */

public class DataBinder<T> extends ConditionalDataBinder<T> {
    public DataBinder(int bindingVariable, int layoutId) {
        super(bindingVariable, layoutId);
    }

    @Override
    public boolean canHandle(T model) {
        return true;
    }
}