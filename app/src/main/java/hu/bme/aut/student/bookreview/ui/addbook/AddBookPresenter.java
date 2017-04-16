package hu.bme.aut.student.bookreview.ui.addbook;

import android.widget.EditText;

import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.model.repository.Repository;
import hu.bme.aut.student.bookreview.ui.base.Presenter;

/**
 * Presenter for the add book page.
 * <p>
 * Created by Daniel Zolnai on 2017-04-16.
 */
public class AddBookPresenter extends Presenter<AddBookScreen> {

    private Repository _repository;

    public AddBookPresenter(Repository repository) {
        _repository = repository;
    }

    public void saveBook(Book book) {
        _repository.saveBook(book);
        _screen.displayToast("Book saved.");
        _screen.navigateBack();
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
