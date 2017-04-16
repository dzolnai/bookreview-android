package hu.bme.aut.student.bookreview.ui.firststart;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DialogTitle;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import hu.bme.aut.student.bookreview.R;
import hu.bme.aut.student.bookreview.databinding.ActivityFirstStartBinding;
import hu.bme.aut.student.bookreview.ui.base.BaseActivity;
import hu.bme.aut.student.bookreview.ui.home.HomeActivity;

public class FirstStartActivity extends BaseActivity<ActivityFirstStartBinding> implements FirstStartScreen {

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
        final UsernameInputDialog usernameInputDialog = new UsernameInputDialog(this, new UsernameInputDialog.UsernameInputListener() {
            @Override
            public void onUsernameSelected(Dialog dialog, String username) {
                Toast.makeText(FirstStartActivity.this, "Selected username: " + username, Toast.LENGTH_LONG).show();
                // TODO check
                _presenter.saveUsername(username);
                dialog.dismiss();
                continueToHomeScreen();
            }
        });
        usernameInputDialog.show();
    }

    @Override
    public void continueToHomeScreen() {
        startActivity(new Intent(FirstStartActivity.this, HomeActivity.class));
        finish();
    }
}
