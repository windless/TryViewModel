package me.zhong.abner.tryviewmodel;

import com.jakewharton.rxbinding.widget.RxTextView;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Abner on 15/8/26.
 */
public class LoginActivity extends Activity {
    private CompositeSubscription _subs = new CompositeSubscription();

    @Bind(R.id.loginView) LoginView _loginView;

    private LoginViewModel _loginViewModel;
    private TextWatcher _emailWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginViewModel = new LoginViewModel(this, AccountManager.INSTANCE);

        _subs.add(RxTextView.textChanges(_loginView.getEmailEdit())
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        _loginViewModel.setEmail(charSequence.toString());
                    }
                }));

        _subs.add(RxTextView.textChanges(_loginView.getPasswordEdit())
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        _loginViewModel.setPassword(charSequence.toString());
                    }
                }));

        _loginView.loginButton.setOnClickListener(_loginViewModel.getLoginCommand());

        _subs.add(_loginViewModel.getIsLoading()
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean isLoading) {
                        if (isLoading) {
                            showLoadingDialog();
                        } else {
                            hideLoadingDialog();
                        }
                    }
                }));

        _subs.add(_loginViewModel.getIsSuccess()
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        finish();
                    }
                }));

        _subs.add(_loginViewModel.getError()
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String errorMessage) {
                        _loginView.getErrorMessage().setText(errorMessage);
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _subs.clear();
        _loginView.loginButton.setOnClickListener(null);

        _loginViewModel.deinit();
    }

    private void showLoadingDialog() {

    }

    private void hideLoadingDialog() {

    }
}
