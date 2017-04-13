package hu.bme.aut.student.bookreview.model.repository;

import android.content.Context;

import java.util.List;

import hu.bme.aut.student.bookreview.model.entity.Book;

/**
 * Repository interface for saving app data.
 * Created by Daniel Zolnai on 2017-04-07.
 */
public interface Repository {
    void open(Context context);

    void close();

    List<Book> getAllBooks();

    void saveBook(Book book);

    void updateBooks(List<Book> books);

    void removeBook(Book book);

    boolean isInDb(Book book);
}
