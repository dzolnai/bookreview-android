package hu.bme.aut.student.bookreview.inject.submodule;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import hu.bme.aut.student.bookreview.inject.subcomponent.BookDetailActivitySubcomponent;
import hu.bme.aut.student.bookreview.inject.subcomponent.FirstStartActivitySubcomponent;
import hu.bme.aut.student.bookreview.ui.bookdetail.BookDetailActivity;
import hu.bme.aut.student.bookreview.ui.firststart.FirstStartActivity;

/**
 * Module for the first start activity.
 * Created by Daniel Zolnai on 2017-03-24.
 */
@Module(subcomponents = BookDetailActivitySubcomponent.class)
public abstract class BookDetailActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(BookDetailActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindFirstActivityInjectorFactory(BookDetailActivitySubcomponent.Builder builder);
}
