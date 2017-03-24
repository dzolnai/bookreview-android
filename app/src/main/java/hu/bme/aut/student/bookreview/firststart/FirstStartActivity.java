package hu.bme.aut.student.bookreview.firststart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import hu.bme.aut.student.bookreview.R;
import hu.bme.aut.student.bookreview.model.service.SettingsService;

public class FirstStartActivity extends AppCompatActivity {

    @Inject
    /* package */ SettingsService _settingsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_first_start);
    }
}
