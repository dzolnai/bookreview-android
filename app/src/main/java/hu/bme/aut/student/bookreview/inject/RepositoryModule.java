package hu.bme.aut.student.bookreview.inject;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.student.bookreview.model.repository.MemoryRepository;
import hu.bme.aut.student.bookreview.model.repository.Repository;
import hu.bme.aut.student.bookreview.model.repository.SugarOrmRepository;

/**
 * Module which provides the services.
 * <p>
 * Created by Daniel Zolnai on 2017-03-24.
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    /* package */ Repository provideRepository() {
        return new SugarOrmRepository();
    }
}
