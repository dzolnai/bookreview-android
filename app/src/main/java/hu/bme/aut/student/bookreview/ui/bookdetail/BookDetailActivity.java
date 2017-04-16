package hu.bme.aut.student.bookreview.ui.bookdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

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
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        String bookId = getIntent().getStringExtra(KEY_BOOK_ID);
        _book = _presenter.getBookForId(bookId);
        _initView();
        _updateView();
    }

    private void _initView() {
        _binding.otherReviewsList.setAdapter(new ReviewAdapter());
        _binding.otherReviewsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
}
