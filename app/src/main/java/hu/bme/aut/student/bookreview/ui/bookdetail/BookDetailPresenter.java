package hu.bme.aut.student.bookreview.ui.bookdetail;

import java.util.List;

import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.model.entity.Review;
import hu.bme.aut.student.bookreview.model.repository.Repository;
import hu.bme.aut.student.bookreview.ui.base.Presenter;

/**
 * Presenter for the book detail page.
 * <p>
 * Created by Daniel Zolnai on 2017-04-16.
 */
public class BookDetailPresenter extends Presenter<BookDetailScreen> {
    private Repository _repository;

    public BookDetailPresenter(Repository repository) {
        _repository = repository;
    }

    public Book getBookForId(String bookId) {
        return _repository.getBookForId(bookId);
    }

    public List<Review> getReviewsForBook(Book book) {
        return _repository.getReviewsForBook(book);
    }

    public void addReviewForBook(Book book, Review review) {
        _repository.addReviewForBook(book, review);
    }


}
