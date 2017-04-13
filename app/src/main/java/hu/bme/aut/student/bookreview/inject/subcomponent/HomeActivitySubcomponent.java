package hu.bme.aut.student.bookreview.inject.subcomponent;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import hu.bme.aut.student.bookreview.ui.firststart.FirstStartActivity;
import hu.bme.aut.student.bookreview.ui.home.HomeActivity;

/**
 * Component which provides the modules for the home activity.
 * Created by Dani on 2017-03-24.
 */
@Subcomponent()
public interface HomeActivitySubcomponent extends AndroidInjector<HomeActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<HomeActivity> {
    }
}
