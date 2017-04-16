package hu.bme.aut.student.bookreview.ui.home;

import android.content.Context;

import java.util.List;

import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.model.repository.Repository;
import hu.bme.aut.student.bookreview.ui.base.Presenter;
import hu.bme.aut.student.bookreview.ui.bookdetail.BookDetailActivity;

/**
 * Presenter for the #{@link HomeActivity}
 * Created by Daniel Zolnai on 2017-04-07.
 */
public class HomePresenter extends Presenter<HomeScreen> {

    private Repository _repository;

    public HomePresenter(Repository repository) {
        _repository = repository;
    }

    public List<Book> getAllBooks() {
        return _repository.getAllBooks();
    }

    public void openBookDetailScreen(Context context, Book book) {
        context.startActivity(BookDetailActivity.newInstance(context, book));
    }
}
