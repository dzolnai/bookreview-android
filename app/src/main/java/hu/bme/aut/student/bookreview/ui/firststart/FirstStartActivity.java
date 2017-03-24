package hu.bme.aut.student.bookreview.ui.firststart;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DialogTitle;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import hu.bme.aut.student.bookreview.R;

public class FirstStartActivity extends AppCompatActivity implements FirstStartScreen {

    @Inject
    protected FirstStartPresenter _presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_first_start);
    }

    @Override
    protected void onStart() {
        super.onStart();
        _presenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        _presenter.detachScreen();
    }

    @Override
    public void promptUsernameInput() {
        final UsernameInputDialog usernameInputDialog = new UsernameInputDialog(this, new UsernameInputDialog.UsernameInputListener() {
            @Override
            public void onUsernameSelected(Dialog dialog, String username) {
                Toast.makeText(FirstStartActivity.this, "Selected username: " + username, Toast.LENGTH_LONG).show();
                // TODO check and save.
                dialog.dismiss();
            }
        });
        usernameInputDialog.show();
    }

    @Override
    public void continueToMainScreen() {
        finish();
        // TODO go to next screen.
    }
}
