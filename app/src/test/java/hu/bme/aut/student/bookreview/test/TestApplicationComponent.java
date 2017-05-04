package hu.bme.aut.student.bookreview.test;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.aut.student.bookreview.inject.ApplicationComponent;
import hu.bme.aut.student.bookreview.inject.submodule.AddBookActivityModule;
import hu.bme.aut.student.bookreview.inject.submodule.BookDetailActivityModule;
import hu.bme.aut.student.bookreview.inject.submodule.FirstStartActivityModule;
import hu.bme.aut.student.bookreview.inject.submodule.HomeActivityModule;
import hu.bme.aut.student.bookreview.ui.firststart.FirstStartPresenterTest;

/**
 * Application component for the unit tests.
 * <p>
 * Created by Daniel Zolnai on 2017-05-04.
 */
@Component(modules = { TestApplicationModule.class, FirstStartActivityModule.class, HomeActivityModule.class,
        BookDetailActivityModule.class, AddBookActivityModule.class })
@Singleton
public interface TestApplicationComponent extends ApplicationComponent {
    void inject(FirstStartPresenterTest firstStartPresenterTest);
}
