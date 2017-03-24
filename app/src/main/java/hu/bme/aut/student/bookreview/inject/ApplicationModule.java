package hu.bme.aut.student.bookreview.inject;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;

/**
 * Application module.
 * <p>
 * Created by Daniel Zolnai on 2017-03-24.
 */
@Module(includes = {AndroidInjectionModule.class, ServiceModule.class, PresenterModule.class})
public class ApplicationModule {

    private final Context _applicationContext;

    public ApplicationModule(Context applicationContext) {
        _applicationContext = applicationContext;
    }

    @Provides
    public Context getApplicationContext() {
        return _applicationContext;
    }
}

