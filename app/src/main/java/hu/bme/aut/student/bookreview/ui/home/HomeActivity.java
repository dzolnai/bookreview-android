package hu.bme.aut.student.bookreview.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;

import hu.bme.aut.student.bookreview.R;
import hu.bme.aut.student.bookreview.databinding.ActivityHomeBinding;
import hu.bme.aut.student.bookreview.ui.adapter.BookAdapter;
import hu.bme.aut.student.bookreview.ui.base.BaseActivity;

/**
 * Activity which displays the list of books.
 * Created by Daniel Zolnai on 2017-04-07.
 */
public class HomeActivity extends BaseActivity<ActivityHomeBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(_binding.toolbar);
        setTitle(R.string.title_home);
        _binding.list.setAdapter(new BookAdapter());
        _binding.list.setLayoutManager(new GridLayoutManager(this, 3));
    }
}
