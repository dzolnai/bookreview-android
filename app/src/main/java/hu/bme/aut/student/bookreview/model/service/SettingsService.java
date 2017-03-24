package hu.bme.aut.student.bookreview.model.service;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Settings which remembers and serves the settings of the current user.
 *
 * Created by Daniel Zolnai on 2017-03-24.
 */
public class SettingsService {

    private static final String KEY_SETTINGS_PREFERENCES = "settings_preferences";

    private Context _applicationContext;

    private String _username;

    public SettingsService(Context context) {
        _applicationContext = context.getApplicationContext();
        _load();
    }

    private void _save() {
        // TODO
    }

    private void _load() {
        // TODO
    }

    private SharedPreferences _getSharedPreferences() {
        return _applicationContext.getSharedPreferences(KEY_SETTINGS_PREFERENCES, Context.MODE_PRIVATE);
    }

    public String getUsername() {
        return _username;
    }

    public void setUsername(String username) {
        _username = username;
        _save();
    }

}
