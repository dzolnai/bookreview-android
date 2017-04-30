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
        _seedRepository();
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

    private void _seedRepository() {
        List<Book> bookList = new ArrayList<>();
        Book book1 = new Book.Builder()
                .setTitle("Metamorphosis")
                .setAuthor("Franz Kafka")
                .setPublishYear(1915)
                .setImageUrl("https://s-media-cache-ak0.pinimg.com/originals/b3/6b/86/b36b86a4b7d012039bc9b3de1a0f81da.jpg")
                .build();
        Book book2 = new Book.Builder()
                .setTitle("Of Mice and Men")
                .setAuthor("John Steinbeck")
                .setPublishYear(1937)
                .setImageUrl("http://files.campus.edublogs.org/blogs.yis.ac.jp/dist/8/325/files/2012/04/original_204690_h7KgDpcspkwwvtIcmszxsVZzf-1y16175.jpg")
                .build();
        Book book3 = new Book.Builder()
                .setTitle("The Book of Strange New Things")
                .setAuthor("Michel Faber")
                .setPublishYear(2014)
                .setImageUrl("https://upload.wikimedia.org/wikipedia/en/thumb/2/2e/BookStrangeNewThings_cover.jpg/200px-BookStrangeNewThings_cover.jpg")
                .build();
        Book book4 = new Book.Builder()
                .setTitle("Go Set a Watchman")
                .setAuthor("Harper Lee")
                .setPublishYear(2015)
                .setImageUrl("https://images-na.ssl-images-amazon.com/images/I/51CC2jfysbL._SX323_BO1,204,203,200_.jpg")
                .build();
        Book book5 = new Book.Builder()
                .setTitle("Inferno")
                .setAuthor("Dan Brown")
                .setPublishYear(2013)
                .setImageUrl("https://upload.wikimedia.org/wikipedia/en/b/bb/Inferno-cover.jpg")
                .build();
        Book book6 = new Book.Builder()
                .setTitle("When Breath Becomes Air")
                .setAuthor("Paul Kalanithi")
                .setPublishYear(2016)
                .setImageUrl("https://cdn.waterstones.com/bookjackets/large/9781/7847/9781784701994.jpg")
                .build();
        Book book7 = new Book.Builder()
                .setTitle("Apollo Expeditions to the Moon - The NASA History")
                .setAuthor("Edgar M. Cortright")
                .setPublishYear(1975)
                .setImageUrl("https://images-na.ssl-images-amazon.com/images/I/5131SAKKp7L._SX383_BO1,204,203,200_.jpg")
                .build();
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);
        bookList.add(book6);
        bookList.add(book7);
        _books = bookList;
    }
}
