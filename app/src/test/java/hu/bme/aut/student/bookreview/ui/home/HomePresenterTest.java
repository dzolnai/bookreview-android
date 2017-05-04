package hu.bme.aut.student.bookreview.ui.home;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.student.bookreview.api.BooksApi;
import hu.bme.aut.student.bookreview.model.entity.Book;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for the home screen.
 * <p>
 * Created by Daniel Zolnai on 2017-05-04.
 */
public class HomePresenterTest {
    private HomePresenter _presenter;
    private BooksApi _mockApi;
    private HomeScreen _screen;

    @Before
    public void setup() throws Exception {
        _mockApi = mock(BooksApi.class);
        when(_mockApi.booksGet()).thenAnswer(new Answer<Single<List<Book>>>() {
            @Override
            public Single<List<Book>> answer(InvocationOnMock invocation) throws Throwable {
                return Single.just(new ArrayList<>());
            }
        });
        _presenter = new HomePresenter(_mockApi);
        _screen = mock(HomeScreen.class);
        _presenter.attachScreen(_screen);
    }

    @Test
    public void testHome() {
        _presenter.getAllBooks();
        verify(_mockApi, atLeastOnce()).booksGet();
    }

    @Test
    public void testSearch() {
        _presenter.search(new ArrayList<>(), "search");
        verify(_screen, atLeastOnce()).displayBooks(any());
        Context mockContext = mock(Context.class);
        _presenter.openBookDetailScreen(mockContext, new Book.Builder().build());
        verify(mockContext, atLeastOnce()).startActivity(any());
    }
}