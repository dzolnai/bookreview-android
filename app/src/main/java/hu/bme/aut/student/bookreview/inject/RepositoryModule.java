package hu.bme.aut.student.bookreview.inject;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.student.bookreview.model.repository.Repository;
import hu.bme.aut.student.bookreview.model.repository.SugarOrmRepository;
import hu.bme.aut.student.bookreview.model.service.SettingsService;

/**
 * Module which provides the services.
 * <p>
 * Created by Daniel Zolnai on 2017-03-24.
 */
@Module
public class RepositoryModule {

    @Provides
    /* package */ Repository provideRepository() {
        return new SugarOrmRepository();
    }
}
