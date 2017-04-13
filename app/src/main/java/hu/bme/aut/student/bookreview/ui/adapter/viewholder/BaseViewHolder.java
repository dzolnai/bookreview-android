package hu.bme.aut.student.bookreview.ui.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Base class for ViewHolder with DataBinding.
 *
 * Created by Daniel Zolnai on 2017-04-07.
 */
public abstract class BaseViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    protected T _binding;

    public BaseViewHolder(View itemView) {
        super(itemView);
        _binding = DataBindingUtil.getBinding(itemView);
    }
}
