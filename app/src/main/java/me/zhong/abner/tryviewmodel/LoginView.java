package me.zhong.abner.tryviewmodel;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Created by Abner on 15/8/26.
 */
public class LoginView extends LinearLayout {
    EditText emailEdit;
    EditText passwordEdit;
    Button loginButton;
    TextView errorMessage;

    public LoginView(Context context) {
        super(context);
    }

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public EditText getEmailEdit() {
        return emailEdit;
    }

    public EditText getPasswordEdit() {
        return passwordEdit;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public TextView getErrorMessage() {
        return errorMessage;
    }

    public void shake() {
    }
}
