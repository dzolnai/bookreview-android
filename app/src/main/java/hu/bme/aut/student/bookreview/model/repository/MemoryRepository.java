package hu.bme.aut.student.bookreview.model.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.student.bookreview.model.entity.Book;

/**
 * Created by Dani on 2017-04-07.
 */

public class MemoryRepository implements Repository {

    private List<Book> _books = new ArrayList<>();

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
        return _books;
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

    }

    @Override
    public boolean isInDb(Book book) {
        return false;
    }
}
