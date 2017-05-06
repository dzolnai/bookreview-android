package hu.bme.aut.student.bookreview.ui.addbook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import hu.bme.aut.student.bookreview.R;
import hu.bme.aut.student.bookreview.databinding.ActivityAddBookBinding;
import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.ui.base.BaseActivity;

/**
 * Activity where the user can add a new book to the list.
 * <p>
 * Created by Daniel Zolnai on 2017-04-16.
 */
public class AddBookActivity extends BaseActivity<ActivityAddBookBinding> implements AddBookScreen {

    @Inject
    protected AddBookPresenter _presenter;

    @Inject
    protected Tracker _tracker;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_book;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        _initToolbar();
        _initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        _tracker.setScreenName("Add book");
        _tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    private void _initToolbar() {
            setSupportActionBar(_binding.toolbar);
            setTitle(R.string.add_book_title);
            if (getSupportActionBar() == null) {
                return;
            }
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void _initView() {
        _binding.addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_presenter.validate(_binding.bookTitle, _binding.bookAuthor, _binding.bookPublishYear, _binding.bookImageUrl)) {
                    Book book = new Book.Builder()
                            .setTitle(_binding.bookTitle.getText().toString())
                            .setAuthor(_binding.bookAuthor.getText().toString())
                            .setPublishYear(Integer.valueOf(_binding.bookPublishYear.getText().toString()))
                            .setImageUrl(_binding.bookImageUrl.getText().toString())
                            .build();
                    _presenter.saveBook(book);
                }
            }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navigateBack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateBack() {
        finish();
    }

    @Override
    public void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
