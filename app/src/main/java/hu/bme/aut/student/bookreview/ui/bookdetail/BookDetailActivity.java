package hu.bme.aut.student.bookreview.ui.bookdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import hu.bme.aut.student.bookreview.R;
import hu.bme.aut.student.bookreview.databinding.ActivityBookDetailBinding;
import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.ui.adapter.ReviewAdapter;
import hu.bme.aut.student.bookreview.ui.base.BaseActivity;

/**
 * Activity which displays the book details.
 * <p>
 * Created by Daniel Zolnai on 2017-04-16.
 */
public class BookDetailActivity extends BaseActivity<ActivityBookDetailBinding> {

    private static final String KEY_BOOK_ID = "key_book_id";

    @Inject
    protected BookDetailPresenter _presenter;

    private Book _book;

    public static Intent newInstance(Context context, Book book) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(KEY_BOOK_ID, book.getId());
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        String bookId = getIntent().getStringExtra(KEY_BOOK_ID);
        _book = _presenter.getBookForId(bookId);
        _initToolbar();
        _initView();
        _updateView();
    }

    private void _initToolbar() {
        setSupportActionBar(_binding.toolbar);
        setTitle(_book.getTitle());
        if (getSupportActionBar() == null) {
            return;
        }
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void _initView() {
        _binding.otherReviewsList.setAdapter(new ReviewAdapter());
        _binding.otherReviewsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        _binding.bookReviewSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer rating = Math.round(_binding.userRating.getRating());
                String comment = _binding.bookReviewComment.getText().toString();
                _presenter.submitReview(_book, rating, comment);
                _binding.bookReviewComment.setText("");
                _binding.userRating.setRating(0);
                _updateView();
            }
        });
    }

    private void _updateView() {
        _binding.bookAuthor.setText(_book.getAuthor());
        _binding.bookTitle.setText(_book.getTitle());
        _binding.bookPublished.setText(getString(R.string.book_published_at, _book.getPublishYear()));
        Picasso.with(this).load(_book.getImageUrl()).into(_binding.bookCover);
        ((ReviewAdapter)_binding.otherReviewsList.getAdapter()).setData(_presenter.getReviewsForBook(_book));
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_book_detail;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
