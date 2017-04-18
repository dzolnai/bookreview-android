package hu.bme.aut.student.bookreview.ui.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import hu.bme.aut.student.bookreview.R;
import hu.bme.aut.student.bookreview.databinding.ActivityHomeBinding;
import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.ui.adapter.BookAdapter;
import hu.bme.aut.student.bookreview.ui.addbook.AddBookActivity;
import hu.bme.aut.student.bookreview.ui.base.BaseActivity;
import hu.bme.aut.student.bookreview.util.GridSpacingItemDecoration;
import hu.bme.aut.student.bookreview.util.ItemClickListener;

/**
 * Activity which displays the list of books.
 * Created by Daniel Zolnai on 2017-04-07.
 */
public class HomeActivity extends BaseActivity<ActivityHomeBinding> implements HomeScreen, SearchView.OnQueryTextListener {

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
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        _binding.addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddBookPage();
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

    @Override
    public void openAddBookPage() {
        startActivity(new Intent(this, AddBookActivity.class));
    }

    @Override
    public void displayBooks(List<Book> allBooks) {
        ((BookAdapter)_binding.list.getAdapter()).setData(allBooks);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.options_menu_main_search));
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by defaultreturn true;
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return _presenter.search(newText);
    }
}
