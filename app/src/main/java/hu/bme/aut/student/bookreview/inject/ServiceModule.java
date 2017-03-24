package hu.bme.aut.student.bookreview.inject;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.student.bookreview.model.service.SettingsService;

/**
 * Module which provides the services.
 * <p>
 * Created by Daniel Zolnai on 2017-03-24.
 */
@Module
public class ServiceModule {

    @Provides
    /* package */ SettingsService provideSettingsService(Context context) {
        return new SettingsService(context);
    }
}
