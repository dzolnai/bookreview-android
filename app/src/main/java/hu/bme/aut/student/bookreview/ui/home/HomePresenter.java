package hu.bme.aut.student.bookreview.ui.home;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hu.bme.aut.student.bookreview.api.BooksApi;
import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.model.repository.Repository;
import hu.bme.aut.student.bookreview.ui.base.Presenter;
import hu.bme.aut.student.bookreview.ui.bookdetail.BookDetailActivity;
import io.reactivex.Single;

/**
 * Presenter for the #{@link HomeActivity}
 * Created by Daniel Zolnai on 2017-04-07.
 */
public class HomePresenter extends Presenter<HomeScreen> {

    private BooksApi _booksApi;

    public HomePresenter(BooksApi booksApi) {
        _booksApi = booksApi;
    }

    public Single<List<Book>> getAllBooks() {
        return _booksApi.booksGet();
    }

    public void openBookDetailScreen(Context context, Book book) {
        context.startActivity(BookDetailActivity.newInstance(context, book));
    }

    public void search(List<Book> books, String newText) {
        List<Book> allBooks = new ArrayList<>(books);
        Iterator<Book> listIterator = allBooks.iterator();
        while (listIterator.hasNext()) {
            Book book = listIterator.next();
            if (book.getTitle().toLowerCase().contains(newText.toLowerCase())) {
                continue;
            }
            if (book.getAuthor().toLowerCase().contains(newText.toLowerCase())) {
                continue;
            }
            listIterator.remove();
        }
        _screen.displayBooks(allBooks);
    }
}
