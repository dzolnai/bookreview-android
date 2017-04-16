package hu.bme.aut.student.bookreview.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import hu.bme.aut.student.bookreview.R;
import hu.bme.aut.student.bookreview.databinding.ActivityHomeBinding;
import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.ui.adapter.BookAdapter;
import hu.bme.aut.student.bookreview.ui.base.BaseActivity;
import hu.bme.aut.student.bookreview.util.GridSpacingItemDecoration;
import hu.bme.aut.student.bookreview.util.ItemClickListener;

/**
 * Activity which displays the list of books.
 * Created by Daniel Zolnai on 2017-04-07.
 */
public class HomeActivity extends BaseActivity<ActivityHomeBinding> implements HomeScreen {

    @Inject
    protected HomePresenter _presenter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setSupportActionBar(_binding.toolbar);
        setTitle(R.string.title_home);
        _initView();
        _updateView();
    }

    public void _initView() {
        BookAdapter adapter = new BookAdapter();
        _binding.list.setAdapter(adapter);
        _binding.list.setLayoutManager(new GridLayoutManager(this, 2));
        _binding.list.addItemDecoration(new GridSpacingItemDecoration(2, 16, true));
        adapter.setItemClickListener(new ItemClickListener<Book>() {
            @Override
            public void onItemClicked(Book item) {
                _presenter.openBookDetailScreen(HomeActivity.this, item);
            }
        });
    }

    public void _updateView() {
        ((BookAdapter)_binding.list.getAdapter()).setData(_presenter.getAllBooks());
    }

    @Override
    protected void onStart() {
        super.onStart();
        _presenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        _presenter.detachScreen();
    }
}
