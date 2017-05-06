package hu.bme.aut.student.bookreview;

import android.app.Activity;
import android.app.Application;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;
import hu.bme.aut.student.bookreview.inject.ApplicationComponent;
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

    private static ApplicationComponent _applicationComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        _applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        _applicationComponent.inject(this);
        _repository.open(this);
    }

    public static void setApplicationComponent(ApplicationComponent applicationComponent) {
        _applicationComponent = applicationComponent;
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return _dispatchingAndroidInjector;
    }

    public static ApplicationComponent getApplicationComponent() {
        return _applicationComponent;
    }
}
