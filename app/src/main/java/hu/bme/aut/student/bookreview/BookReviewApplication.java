package hu.bme.aut.student.bookreview;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;
import hu.bme.aut.student.bookreview.inject.DaggerApplicationComponent;

/**
 * Main application.
 * <p>
 * Created by Daniel Zolnai on 2017-03-24.
 */
public class BookReviewApplication extends Application implements HasDispatchingActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> _dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApplicationComponent.create().inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return _dispatchingAndroidInjector;
    }
}
