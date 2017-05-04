package hu.bme.aut.student.bookreview.inject;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.student.bookreview.api.BooksApi;
import hu.bme.aut.student.bookreview.api.ReviewsApi;
import hu.bme.aut.student.bookreview.api.UsersApi;
import hu.bme.aut.student.bookreview.model.repository.Repository;
import hu.bme.aut.student.bookreview.model.service.SettingsService;
import hu.bme.aut.student.bookreview.ui.adapter.BookAdapter;
import hu.bme.aut.student.bookreview.ui.addbook.AddBookPresenter;
import hu.bme.aut.student.bookreview.ui.bookdetail.BookDetailPresenter;
import hu.bme.aut.student.bookreview.ui.firststart.FirstStartPresenter;
import hu.bme.aut.student.bookreview.ui.home.HomePresenter;

/**
 * Module which provides the presenters.
 * <p>
 * Created by Daniel Zolnai on 2017-03-24.
 */
@Module
public class PresenterModule {

    @Provides
    /* package */ FirstStartPresenter provideFirstStartPresenter(SettingsService settingsService, UsersApi usersApi) {
        return new FirstStartPresenter(settingsService, usersApi);
    }

    @Provides
    /* package */ HomePresenter provideHomePresenter(BooksApi booksApi) {
        return new HomePresenter(booksApi);
    }

    @Provides
    /* package */ BookDetailPresenter provideBookDetailPresenter(SettingsService settingsService, ReviewsApi reviewsApi) {
        return new BookDetailPresenter(settingsService, reviewsApi);
    }

    @Provides
    /* package */ AddBookPresenter provideAddBookPresenter(BooksApi booksApi) {
        return new AddBookPresenter(booksApi);
    }
}
