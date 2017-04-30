package hu.bme.aut.student.bookreview.ui.firststart;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import hu.bme.aut.student.bookreview.R;

/**
 * Username input dialog.
 * Created by Dani on 2017-03-24.
 */
public class UsernameInputDialog extends Dialog {

    private EditText _usernameInput;

    private UsernameInputListener _listener;

    public interface UsernameInputListener {
        void onUsernameSelected(Dialog dialog, String username);
    }

    public UsernameInputDialog(@NonNull Context context, @NonNull UsernameInputListener listener) {
        super(context, R.style.Theme_AppCompat_Dialog);
        _listener = listener;
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_username_input);
        _usernameInput = (EditText)findViewById(R.id.dialog_username_input_username);
        findViewById(R.id.dialog_username_button_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = _usernameInput.getText().toString();
                _listener.onUsernameSelected(UsernameInputDialog.this, username);
            }
        });
    }
}
