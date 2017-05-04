package hu.bme.aut.student.bookreview.ui.firststart;

import hu.bme.aut.student.bookreview.api.UsersApi;
import hu.bme.aut.student.bookreview.model.entity.User;
import hu.bme.aut.student.bookreview.model.service.SettingsService;
import hu.bme.aut.student.bookreview.ui.base.Presenter;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter for the @{#link {@link FirstStartActivity}.
 * <p>
 * Created by Daniel Zolnai on 2017-03-24.
 */
public class FirstStartPresenter extends Presenter<FirstStartScreen> {

    private SettingsService _settingsService;
    private UsersApi _usersApi;

    public FirstStartPresenter(SettingsService settingsService, UsersApi usersApi) {
        _settingsService = settingsService;
        _usersApi = usersApi;
    }

    @Override
    public void attachScreen(FirstStartScreen screen) {
        super.attachScreen(screen);
        if (_settingsService.getUsername() != null) {
            screen.continueToHomeScreen();
        } else {
            screen.promptUsernameInput();
        }
    }

    public void saveUsername(String username) {
        _settingsService.setUsername(username);
    }

    public Completable checkUsername(String username) {
        return _usersApi.userCheckGet(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable registerUsername(String username) {
        return _usersApi.registerUser(new User(username))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
