package hu.bme.aut.student.bookreview.model.repository;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.model.entity.Review;

/**
 * Repository which saves the items with SugarORM.
 * Created by Daniel Zolnai on 2017-04-07.
 */
public class SugarOrmRepository implements Repository {
    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public List<Book> getAllBooks() {
        return SugarRecord.listAll(Book.class);
    }

    @Override
    public void saveBook(Book book) {
        SugarRecord.saveInTx(book);
    }

    @Override
    public void updateBooks(List<Book> books) {
        List<Book> savedBooks = getAllBooks();
        List<Book> toUpdate = new ArrayList<>(books.size());
        for (Book bookToUpdate : books) {
            for (Book book : savedBooks) {
                if (book.getId().equals(bookToUpdate.getId())) {
                    toUpdate.add(bookToUpdate);
                }
            }
        }
        SugarRecord.saveInTx(toUpdate);
    }

    @Override
    public void removeBook(Book book) {
        SugarRecord.deleteInTx(book);
    }

    @Override
    public boolean isInDb(Book book) {
        return SugarRecord.findById(Book.class, new String[] { book.getId() }) != null;
    }

    @Override
    public List<Review> getReviewsForBook(Book book) {
        return SugarRecord.find(Review.class, "_bookId = ?", book.getId());
    }

    @Override
    public void addReviewForBook(Book book, Review review) {
        SugarRecord.save(review);
    }

    @Override
    public Book getBookForId(String bookId) {
        List<Book> result = SugarRecord.findById(Book.class, new String[] { bookId });
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }
}
