package hu.bme.aut.student.bookreview.model.repository;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.student.bookreview.model.entity.Book;

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
}
