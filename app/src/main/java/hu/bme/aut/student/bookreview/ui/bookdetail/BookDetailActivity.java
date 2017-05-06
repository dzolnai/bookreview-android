package hu.bme.aut.student.bookreview.ui.bookdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import hu.bme.aut.student.bookreview.R;
import hu.bme.aut.student.bookreview.databinding.ActivityBookDetailBinding;
import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.ui.adapter.ReviewAdapter;
import hu.bme.aut.student.bookreview.ui.base.BaseActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Activity which displays the book details.
 * <p>
 * Created by Daniel Zolnai on 2017-04-16.
 */
public class BookDetailActivity extends BaseActivity<ActivityBookDetailBinding> {

    private static final String TAG = BookDetailActivity.class.getName();

    private static final String KEY_BOOK = "key_book";

    @Inject
    protected BookDetailPresenter _presenter;

    @Inject
    protected Tracker _tracker;

    private Book _book;

    public static Intent newInstance(Context context, Book book) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(KEY_BOOK, Parcels.wrap(book));
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        _book = Parcels.unwrap(getIntent().getParcelableExtra(KEY_BOOK));
        _initToolbar();
        _initView();
        _updateView();
        _fetchReviews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        _tracker.setScreenName("Book detail");
        _tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    private void _fetchReviews() {
        _presenter.getReviewsForBook(_book.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(reviews -> {
                    ((ReviewAdapter)_binding.otherReviewsList.getAdapter()).setData(reviews);
                    _updateView();
                }, throwable -> Log.e(TAG, "Error fetching reviews for book!", throwable));

    }

    private void _initToolbar() {
        setSupportActionBar(_binding.toolbar);
        if (_book != null) {
            setTitle(_book.getTitle());
        }
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
        _binding.bookReviewSubmitButton.setOnClickListener(v -> {
            Integer rating = Math.round(_binding.userRating.getRating());
            String comment = _binding.bookReviewComment.getText().toString();
            _presenter.submitReview(_book, rating, comment);
            _binding.bookReviewComment.setText("");
            _binding.userRating.setRating(0);
            _fetchReviews();
        });
    }

    private void _updateView() {
        if (_book == null) {
            return;
        }
        _binding.bookAuthor.setText(_book.getAuthor());
        _binding.bookTitle.setText(_book.getTitle());
        _binding.bookPublished.setText(getString(R.string.book_published_at, _book.getPublishYear()));
        Picasso.with(this).load(_book.getImageUrl()).into(_binding.bookCover);
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
