package hu.bme.aut.student.bookreview.inject.subcomponent;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import hu.bme.aut.student.bookreview.ui.firststart.FirstStartActivity;

/**
 * Component which provides the modules for the first activity.
 * Created by Dani on 2017-03-24.
 */
@Subcomponent()
public interface FirstStartActivitySubcomponent extends AndroidInjector<FirstStartActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<FirstStartActivity> {
    }
}
