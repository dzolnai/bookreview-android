package hu.bme.aut.student.bookreview.ui.addbook;

import android.widget.EditText;

import hu.bme.aut.student.bookreview.api.BooksApi;
import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.ui.base.Presenter;
import io.reactivex.functions.Consumer;

/**
 * Presenter for the add book page.
 * <p>
 * Created by Daniel Zolnai on 2017-04-16.
 */
public class AddBookPresenter extends Presenter<AddBookScreen> {

    private BooksApi _booksApi;

    public AddBookPresenter(BooksApi booksApi) {
        _booksApi = booksApi;
    }

    public void saveBook(Book book) {
        _booksApi.booksPost(book).subscribe(() -> {
            _screen.displayToast("Book saved.");
            _screen.navigateBack();
        }, throwable -> _screen.displayToast("Unable to save book."));
    }

    public boolean validate(EditText titleText,
                            EditText authorText,
                            EditText publishYearText,
                            @SuppressWarnings("UnusedParameters") EditText imageUrlText) {
        if (titleText.getText().length() <= 0) {
            titleText.setError("Title can not be empty!");
            return false;
        }
        if (authorText.getText().length() <= 0) {
            authorText.setError("Author can not be empty!");
            return false;
        }
        if (publishYearText.getText().length() <= 0) {
            publishYearText.setError("Publish year can not be empty!");
            return false;
        }
        return true;
    }


}
