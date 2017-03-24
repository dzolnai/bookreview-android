package hu.bme.aut.student.bookreview.inject;

import dagger.Component;
import hu.bme.aut.student.bookreview.BookReviewApplication;
import hu.bme.aut.student.bookreview.inject.submodule.FirstStartActivityModule;

/**
 * The main application component which does the injections.
 * Created by Dani on 2017-03-24.
 */
@Component(modules = { FirstStartActivityModule.class })
public interface ApplicationComponent {
    void inject(BookReviewApplication application);
}
