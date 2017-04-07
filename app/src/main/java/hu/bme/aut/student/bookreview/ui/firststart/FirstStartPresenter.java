package hu.bme.aut.student.bookreview.ui.firststart;

import hu.bme.aut.student.bookreview.model.service.SettingsService;
import hu.bme.aut.student.bookreview.ui.base.Presenter;

/**
 * Presenter for the @{#link {@link FirstStartActivity}.
 * <p>
 * Created by Daniel Zolnai on 2017-03-24.
 */
public class FirstStartPresenter extends Presenter<FirstStartScreen> {

    private SettingsService _settingsService;

    public FirstStartPresenter(SettingsService settingsService) {
        _settingsService = settingsService;
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
}
