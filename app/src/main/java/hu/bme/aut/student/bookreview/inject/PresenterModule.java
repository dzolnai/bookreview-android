package hu.bme.aut.student.bookreview.inject;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.student.bookreview.model.service.SettingsService;
import hu.bme.aut.student.bookreview.ui.firststart.FirstStartPresenter;

/**
 * Module which provides the presenters.
 * <p>
 * Created by Daniel Zolnai on 2017-03-24.
 */
@Module
public class PresenterModule {

    @Provides
    /* package */ FirstStartPresenter provideFirstStartPresenter(SettingsService settingsService) {
        return new FirstStartPresenter(settingsService);
    }
}
