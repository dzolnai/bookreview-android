package hu.bme.aut.student.bookreview.test;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import hu.bme.aut.student.bookreview.inject.PresenterModule;
import hu.bme.aut.student.bookreview.inject.RepositoryModule;
import hu.bme.aut.student.bookreview.inject.ServiceModule;
import hu.bme.aut.student.bookreview.mock.MockNetworkModule;

/**
 * Main module for the unit tests.
 * Created by Daniel Zolnai on 2017-05-04.
 */
@Module(includes = { AndroidInjectionModule.class, ServiceModule.class,
        PresenterModule.class, RepositoryModule.class, MockNetworkModule.class })
public class TestApplicationModule {
    private final Context _applicationContext;

    public TestApplicationModule(Context applicationContext) {
        _applicationContext = applicationContext;
    }

    @Provides
    public Context getApplicationContext() {
        return _applicationContext;
    }
}
