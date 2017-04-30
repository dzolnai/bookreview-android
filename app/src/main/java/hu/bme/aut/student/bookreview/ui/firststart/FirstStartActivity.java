package hu.bme.aut.student.bookreview.ui.firststart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import hu.bme.aut.student.bookreview.R;
import hu.bme.aut.student.bookreview.databinding.ActivityFirstStartBinding;
import hu.bme.aut.student.bookreview.ui.base.BaseActivity;
import hu.bme.aut.student.bookreview.ui.home.HomeActivity;
import io.reactivex.functions.Consumer;

public class FirstStartActivity extends BaseActivity<ActivityFirstStartBinding> implements FirstStartScreen {

    private static final String TAG = FirstStartActivity.class.getName();

    @Inject
    protected FirstStartPresenter _presenter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_first_start;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
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
        final UsernameInputDialog usernameInputDialog = new UsernameInputDialog(this, (dialog, username) -> {
            Toast.makeText(FirstStartActivity.this, "Selected username: " + username, Toast.LENGTH_LONG).show();
            _checkUsername(username);
            _presenter.saveUsername(username);
            dialog.dismiss();
            continueToHomeScreen();
        });
        usernameInputDialog.show();
    }

    private void _checkUsername(String username) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Checking username...");
        progressDialog.setMessage("Checking if username is available...");
        progressDialog.show();
        _presenter.checkUsername(username)
                .subscribe(aVoid -> {
                    progressDialog.setTitle("Registering...");
                    progressDialog.setMessage("Requesting username...");
                    _registerUsername(username, progressDialog);
                }, throwable -> {
                    progressDialog.dismiss();
                    Log.e(TAG, "Error getting username", throwable);
                    Toast.makeText(FirstStartActivity.this, "Error while getting username. Please try another username.", Toast.LENGTH_LONG).show();
                });
    }

    private void _registerUsername(String username, ProgressDialog progressDialog) {
        _presenter.registerUsername(username)
                .subscribe(aVoid -> {
                    progressDialog.dismiss();
                    _presenter.saveUsername(username);
                    continueToHomeScreen();
                }, throwable -> {
                    progressDialog.dismiss();
                    Log.e(TAG, "Error registering", throwable);
                    Toast.makeText(FirstStartActivity.this, "Error while registering. Please try another username.", Toast.LENGTH_LONG).show();
                });
    }

    @Override
    public void continueToHomeScreen() {
        startActivity(new Intent(FirstStartActivity.this, HomeActivity.class));
        finish();
    }
}
