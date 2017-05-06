package hu.bme.aut.student.bookreview.inject;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.student.bookreview.R;
import hu.bme.aut.student.bookreview.model.service.SettingsService;

/**
 * Module which provides the services.
 * <p>
 * Created by Daniel Zolnai on 2017-03-24.
 */
@Module
public class ServiceModule {

    @Provides
    @Singleton
    /* package */ SettingsService provideSettingsService(Context context) {
        return new SettingsService(context);
    }

    @Provides
    @Singleton
    /* package */ Tracker provideAnalyticsTracker(Context context) {
        return GoogleAnalytics.getInstance(context).newTracker(R.xml.global_tracker);
    }
}
