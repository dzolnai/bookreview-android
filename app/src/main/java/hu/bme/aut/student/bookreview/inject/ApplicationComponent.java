package hu.bme.aut.student.bookreview.inject;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.aut.student.bookreview.BookReviewApplication;
import hu.bme.aut.student.bookreview.inject.submodule.AddBookActivityModule;
import hu.bme.aut.student.bookreview.inject.submodule.BookDetailActivityModule;
import hu.bme.aut.student.bookreview.inject.submodule.FirstStartActivityModule;
import hu.bme.aut.student.bookreview.inject.submodule.HomeActivityModule;

/**
 * The main application component which does the injections.
 * Created by Dani on 2017-03-24.
 */
@Component(modules = { ApplicationModule.class, FirstStartActivityModule.class, HomeActivityModule.class,
        BookDetailActivityModule.class, AddBookActivityModule.class })
@Singleton
public interface ApplicationComponent {
    void inject(BookReviewApplication application);
}
