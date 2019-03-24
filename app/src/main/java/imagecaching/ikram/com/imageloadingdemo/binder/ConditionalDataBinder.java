package imagecaching.ikram.com.imageloadingdemo.binder;

/**
 * Created by ikram on 24/03/2019.
 */

public abstract class ConditionalDataBinder<T> extends ItemBinderBase<T>
{
    public ConditionalDataBinder(int bindingVariable, int layoutId)
    {
        super(bindingVariable, layoutId);
    }

    public abstract boolean canHandle(T model);

    public  boolean canHandle(int layoutId)
    {
        return this.layoutId == layoutId;
    }
}