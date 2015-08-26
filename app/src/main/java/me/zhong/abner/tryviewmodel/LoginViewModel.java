package me.zhong.abner.tryviewmodel;

import android.content.Context;
import android.view.View;

import java.io.IOException;
import java.security.InvalidKeyException;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Abner on 15/8/26.
 */
public class LoginViewModel {
    final private PublishSubject<Boolean> _isLoading = PublishSubject.create();
    final private PublishSubject<Boolean> _isSuccess = PublishSubject.create();
    final private PublishSubject<String>  _error     = PublishSubject.create();
    final private CompositeSubscription   _subs      = new CompositeSubscription();

    private final Context        _context;
    private final AccountManager _accountManager;

    private String _email;
    private String _password;

    public LoginViewModel(Context context, AccountManager accountManager) {
        _context = context;
        _accountManager = accountManager;
    }

    public View.OnClickListener getLoginCommand() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        };
    }

    public Observable<Boolean> getIsLoading() {
        return _isLoading.asObservable();
    }

    public Observable<Boolean> getIsSuccess() {
        return _isSuccess.asObservable();
    }

    public Observable<String> getError() {
        return _error.asObservable();
    }

    public void setEmail(String email) {
        _email = email;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public void deinit() {
        _subs.clear();
    }

    private void login() {
        _isLoading.onNext(true);

        _subs.clear();

        Subscription subscribe = _accountManager.login(_email, _password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Account>() {
                    @Override
                    public void call(Account account) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        _isLoading.onNext(false);
                        if (throwable instanceof IOException) {
                            _error.onNext(_context.getString(R.string.invalid_email_or_password));
                        } else if (throwable instanceof InvalidKeyException) {
                            _error.onNext(_context.getString(R.string.invalid_config));
                        }
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        _isLoading.onNext(false);
                        _isSuccess.onNext(true);
                    }
                });
        _subs.add(subscribe);
    }
}
