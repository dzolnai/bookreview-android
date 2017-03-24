package hu.bme.aut.student.bookreview.inject.submodule;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import hu.bme.aut.student.bookreview.firststart.FirstStartActivity;
import hu.bme.aut.student.bookreview.inject.subcomponent.FirstStartActivitySubcomponent;

/**
 * Module for the first start activity.
 * Created by Daniel Zolnai on 2017-03-24.
 */
@Module(subcomponents = FirstStartActivitySubcomponent.class)
public abstract class FirstStartActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(FirstStartActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindFirstActivityInjectorFactory(FirstStartActivitySubcomponent.Builder builder);
}
