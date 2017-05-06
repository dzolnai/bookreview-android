package hu.bme.aut.student.bookreview.ui.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

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

/**
 * Activity which displays the list of books.
 * Created by Daniel Zolnai on 2017-04-07.
 */
public class HomeActivity extends BaseActivity<ActivityHomeBinding> implements HomeScreen, SearchView.OnQueryTextListener {

    private static final String TAG = HomeActivity.class.getName();

    @Inject
    protected HomePresenter _presenter;

    @Inject
    protected Tracker _tracker;

    private List<Book> _books;

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
        _tracker.setScreenName("Home");
        _tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public void _initView() {
        BookAdapter adapter = new BookAdapter();
        _binding.list.setAdapter(adapter);
        _binding.list.setLayoutManager(new GridLayoutManager(this, 2));
        _binding.list.addItemDecoration(new GridSpacingItemDecoration(2, 16, true));
        adapter.setItemClickListener(item -> _presenter.openBookDetailScreen(HomeActivity.this, item));
        _binding.addBookButton.setOnClickListener(view -> openAddBookPage());
    }

    public void _updateView() {
        _presenter.getAllBooks().subscribe(books -> {
            ((BookAdapter)_binding.list.getAdapter()).setData(books);
            _books = books;
        }, throwable -> {
            Toast.makeText(HomeActivity.this, "Unable to fetch list of books!", Toast.LENGTH_LONG).show();
            Log.e(TAG, "Error fetching list of books", throwable);
        });
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
        SearchView searchView = (SearchView)MenuItemCompat.getActionView(menu.findItem(R.id.options_menu_main_search));
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
        if (_books != null) {
            _presenter.search(_books, newText);
        }
        return false;
    }
}
