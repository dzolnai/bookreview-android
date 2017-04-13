package hu.bme.aut.student.bookreview;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;
import hu.bme.aut.student.bookreview.inject.ApplicationModule;
import hu.bme.aut.student.bookreview.inject.DaggerApplicationComponent;
import hu.bme.aut.student.bookreview.model.repository.Repository;

/**
 * Main application.
 * <p>
 * Created by Daniel Zolnai on 2017-03-24.
 */
public class BookReviewApplication extends Application implements HasDispatchingActivityInjector {

    @Inject
    protected DispatchingAndroidInjector<Activity> _dispatchingAndroidInjector;

    @Inject
    protected Repository _repository;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build()
                .inject(this);
        _repository.open(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return _dispatchingAndroidInjector;
    }
}
