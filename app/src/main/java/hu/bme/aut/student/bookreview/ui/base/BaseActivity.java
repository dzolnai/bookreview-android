package hu.bme.aut.student.bookreview.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Base activity with extra databinding features. All activities should extends this.
 *
 * Created by Dani on 2017-04-07.
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    protected T _binding;

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = DataBindingUtil.setContentView(this, getLayoutRes());
        onBindingLayoutInflated(_binding);
    }

    /**
     * Convenient method to have an organised place to get the views from the binding
     *
     * @param binding The binding containing the views.
     */
    protected void onBindingLayoutInflated(T binding) {
        // Override this if needed.
    }
}
