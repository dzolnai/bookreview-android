package hu.bme.aut.student.bookreview.ui.bookdetail;

import android.util.Log;

import java.util.List;

import hu.bme.aut.student.bookreview.api.ReviewsApi;
import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.model.entity.Review;
import hu.bme.aut.student.bookreview.model.service.SettingsService;
import hu.bme.aut.student.bookreview.ui.base.Presenter;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter for the book detail page.
 * <p>
 * Created by Daniel Zolnai on 2017-04-16.
 */
public class BookDetailPresenter extends Presenter<BookDetailScreen> {

    private static final String TAG = BookDetailPresenter.class.getName();

    private ReviewsApi _reviewsApi;
    private SettingsService _settingsService;

    public BookDetailPresenter(SettingsService settingsService, ReviewsApi reviewsApi) {
        _reviewsApi = reviewsApi;
        _settingsService = settingsService;
    }

    public Single<List<Review>> getReviewsForBook(String bookId) {
        return _reviewsApi.booksIdReviewsGet(bookId);
    }

    public void addReviewForBook(Review review) {
        _reviewsApi.booksIdReviewsPost(review)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(aVoid -> Log.i(TAG, "Successfully posted review for book."), throwable -> Log.e(TAG, "Error posting review for book!", throwable));
    }

    public void submitReview(Book book, Integer rating, String comment) {
        Review review = new Review(_settingsService.getUsername(), book.getId(), rating, comment);
        addReviewForBook(review);
    }
}
