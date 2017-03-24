package hu.bme.aut.student.bookreview.ui.firststart;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.bme.aut.student.bookreview.R;

/**
 * Username input dialog.
 * Created by Dani on 2017-03-24.
 */
public class UsernameInputDialog extends Dialog {

    @BindView(R.id.dialog_username_input_username)
    protected EditText _usernameInput;

    private UsernameInputListener _listener;

    public interface UsernameInputListener {
        void onUsernameSelected(Dialog dialog, String username);
    }

    public UsernameInputDialog(@NonNull Context context, @NonNull UsernameInputListener listener) {
        super(context, R.style.Theme_AppCompat_Dialog);
        _listener = listener;
        setContentView(R.layout.dialog_username_input);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.dialog_username_button_start)
    protected void _onStartButtonClicked() {
        String username = _usernameInput.getText().toString();
        _listener.onUsernameSelected(UsernameInputDialog.this, username);
    }
}
