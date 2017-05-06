package hu.bme.aut.student.bookreview.model.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Settings which remembers and serves the settings of the current user.
 * <p>
 * Created by Daniel Zolnai on 2017-03-24.
 */
public class SettingsService {

    private static final String KEY_SETTINGS_PREFERENCES = "settings_preferences";

    private static final String KEY_USERNAME = "key_username";

    private Context _applicationContext;

    private String _username;

    public SettingsService(Context context) {
        _applicationContext = context.getApplicationContext();
        _load();
    }

    @SuppressLint("ApplySharedPref")
    public static void reset(Context context) {
        context.getSharedPreferences(KEY_SETTINGS_PREFERENCES, Context.MODE_PRIVATE).edit().clear().commit();
    }

    private void _save() {
        _getSharedPreferences().edit().putString(KEY_USERNAME, _username).apply();
    }

    private void _load() {
        SharedPreferences preferences = _getSharedPreferences();
        _username = preferences.getString(KEY_USERNAME, null);
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
