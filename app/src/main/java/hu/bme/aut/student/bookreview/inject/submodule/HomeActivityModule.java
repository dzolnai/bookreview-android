package hu.bme.aut.student.bookreview.inject.submodule;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import hu.bme.aut.student.bookreview.inject.subcomponent.FirstStartActivitySubcomponent;
import hu.bme.aut.student.bookreview.inject.subcomponent.HomeActivitySubcomponent;
import hu.bme.aut.student.bookreview.ui.firststart.FirstStartActivity;
import hu.bme.aut.student.bookreview.ui.home.HomeActivity;

/**
 * Module for the home activity.
 * Created by Daniel Zolnai on 2017-04-13.
 */
@Module(subcomponents = HomeActivitySubcomponent.class)
public abstract class HomeActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(HomeActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindHomeActivityInjectorFactory(HomeActivitySubcomponent.Builder builder);
}
