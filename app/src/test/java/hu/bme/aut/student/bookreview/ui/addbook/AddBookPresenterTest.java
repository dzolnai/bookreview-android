package hu.bme.aut.student.bookreview.ui.addbook;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import hu.bme.aut.student.bookreview.api.BooksApi;
import hu.bme.aut.student.bookreview.model.entity.Book;
import io.reactivex.Completable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.description;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for the add book screen.
 * Created by Daniel Zolnai on 2017-05-04.
 */
public class AddBookPresenterTest {
    private AddBookPresenter _presenter;
    private BooksApi _mockApi;
    private AddBookScreen _screen;

    @Before
    public void setup() throws Exception {
        _mockApi = mock(BooksApi.class);
        when(_mockApi.booksPost(any(Book.class))).thenAnswer(new Answer<Completable>() {
            @Override
            public Completable answer(InvocationOnMock invocation) throws Throwable {
                return Completable.complete();
            }
        });
        _presenter = new AddBookPresenter(_mockApi);
        _screen = mock(AddBookScreen.class);
        _presenter.attachScreen(_screen);
    }

    @Test
    public void testAddBook() {
        Book book = new Book.Builder().build();
        _presenter.saveBook(book);
        verify(_mockApi, times(1)).booksPost(book);
        verify(_screen, times(1)).displayToast(anyString());
        verify(_screen, times(1)).navigateBack();
    }
}