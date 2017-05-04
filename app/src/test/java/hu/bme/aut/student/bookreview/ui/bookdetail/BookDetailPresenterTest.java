package hu.bme.aut.student.bookreview.ui.bookdetail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.student.bookreview.api.ReviewsApi;
import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.model.entity.Review;
import hu.bme.aut.student.bookreview.model.service.SettingsService;
import io.reactivex.Completable;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for the book detail presenter.
 * <p>
 * Created by Daniel Zolnai on 2017-05-04.
 */
public class BookDetailPresenterTest {
    private BookDetailPresenter _presenter;
    private SettingsService _mockSettings;
    private ReviewsApi _mockApi;

    @Before
    public void setup() throws Exception {
        _mockSettings = mock(SettingsService.class);
        _mockApi = mock(ReviewsApi.class);
        when(_mockApi.booksIdReviewsPost(any(Review.class))).thenAnswer(new Answer<Completable>() {
            @Override
            public Completable answer(InvocationOnMock invocation) throws Throwable {
                return Completable.complete();
            }
        });
        when(_mockApi.booksIdReviewsGet(anyString())).thenAnswer(new Answer<Single<List<Review>>>() {
            @Override
            public Single<List<Review>> answer(InvocationOnMock invocation) throws Throwable {
                return Single.just(new ArrayList<>());
            }
        });
        _presenter = new BookDetailPresenter(_mockSettings, _mockApi);
    }

    @Test
    public void testPostReview() {
        BookDetailScreen screen = mock(BookDetailScreen.class);
        _presenter.attachScreen(screen);
        _presenter.submitReview(new Book.Builder().build(), 1, "");
        verify(_mockApi, times(1)).booksIdReviewsPost(any());
        verify(_mockSettings, times(1)).getUsername();
    }

    @Test
    public void testGetReviews() {
        _presenter.getReviewsForBook("1");
        verify(_mockApi, times(1)).booksIdReviewsGet("1");
    }
}