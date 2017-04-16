package hu.bme.aut.student.bookreview.inject.submodule;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import hu.bme.aut.student.bookreview.inject.subcomponent.AddBookActivitySubcomponent;
import hu.bme.aut.student.bookreview.inject.subcomponent.HomeActivitySubcomponent;
import hu.bme.aut.student.bookreview.ui.addbook.AddBookActivity;
import hu.bme.aut.student.bookreview.ui.home.HomeActivity;

/**
 * Module for the home activity.
 * Created by Daniel Zolnai on 2017-04-13.
 */
@Module(subcomponents = AddBookActivitySubcomponent.class)
public abstract class AddBookActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(AddBookActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindAddBookActivityInjectorFactory(AddBookActivitySubcomponent.Builder builder);
}
