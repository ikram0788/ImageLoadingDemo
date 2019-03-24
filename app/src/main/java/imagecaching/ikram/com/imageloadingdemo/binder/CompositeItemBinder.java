package imagecaching.ikram.com.imageloadingdemo.binder;

/**
 * Created by ikram on 24/03/2019.
 */

public class CompositeItemBinder<T> implements ItemBinder<T> {
    private final ConditionalDataBinder<T>[] conditionalDataBinders;

    public CompositeItemBinder(ConditionalDataBinder<T>... conditionalDataBinders) {
        this.conditionalDataBinders = conditionalDataBinders;
    }

    @Override
    public int getLayoutRes(T model) {
        for (ConditionalDataBinder<T> dataBinder :
                conditionalDataBinders) {
            //ConditionalDataBinder<T> dataBinder = conditionalDataBinders[i];
            if (dataBinder.canHandle(model)) {
                return dataBinder.getLayoutRes(model);
            }
        }

        throw new IllegalStateException();
    }

    @Override
    public int getBindingVariable(T model) {
        for (ConditionalDataBinder<T> dataBinder :
                conditionalDataBinders) {
//            ConditionalDataBinder<T> dataBinder = conditionalDataBinders[i];
            if (dataBinder.canHandle(model)) {
                return dataBinder.getBindingVariable(model);
            }
        }

        throw new IllegalStateException();
    }
}
