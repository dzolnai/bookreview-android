package hu.bme.aut.student.bookreview.model.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.model.entity.Review;

/**
 * Repository which stores items in the memory.
 * <p>
 * Created by Daniel Zolnai on 2017-04-07.
 */
public class MemoryRepository implements Repository {

    private List<Book> _books = new ArrayList<>();
    private Map<String, List<Review>> _reviews = new HashMap<>();

    @Override
    public void open(Context context) {
        // No-op
    }

    @Override
    public void close() {
        // No-op
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(_books);
    }

    @Override
    public void saveBook(Book book) {
        _books.add(book);
    }

    @Override
    public void updateBooks(List<Book> books) {
        for (int i = 0; i < _books.size(); ++i) {
            Book book = _books.get(i);
            for (Book bookToUpdate : books) {
                if (bookToUpdate.getId().equals(book.getId())) {

                }
            }
        }
    }

    @Override
    public void removeBook(Book book) {
        _books.remove(book);
    }

    @Override
    public boolean isInDb(Book book) {
        return false;
    }

    @Override
    public List<Review> getReviewsForBook(String bookId) {
        if (!_reviews.containsKey(bookId)) {
            return new ArrayList<>();
        } else {
            return _reviews.get(bookId);
        }
    }

    @Override
    public void addReview(Review review) {
        if (_reviews.containsKey(review.getBookId())) {
            _reviews.get(review.getBookId()).add(review);
        } else {
            List<Review> bookReviews = new ArrayList<>();
            bookReviews.add(review);
            _reviews.put(review.getBookId(), bookReviews);
        }
    }

    @Override
    public Book getBookForId(String bookId) {
        for (Book book : _books) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }
}
