package hu.bme.aut.student.bookreview.inject.subcomponent;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import hu.bme.aut.student.bookreview.ui.addbook.AddBookActivity;
import hu.bme.aut.student.bookreview.ui.home.HomeActivity;

/**
 * Component which provides the modules for the home activity.
 * Created by Dani on 2017-03-24.
 */
@Subcomponent()
public interface AddBookActivitySubcomponent extends AndroidInjector<AddBookActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<AddBookActivity> {
    }
}
