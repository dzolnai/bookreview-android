package hu.bme.aut.student.bookreview.ui.home;

import java.util.List;

import hu.bme.aut.student.bookreview.model.entity.Book;

/**
 * Screen for the #{@link HomeActivity}.
 * Created by Daniel Zolnai on 2017-04-07.
 */
public interface HomeScreen {
    void openAddBookPage();

    void displayBooks(List<Book> allBooks);
}
