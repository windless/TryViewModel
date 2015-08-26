package me.zhong.abner.tryviewmodel;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Abner on 15/8/26.
 */
public enum AccountManager {
    INSTANCE;

    public Observable<Account> login(String email, String password) {
        return Observable.create(new Observable.OnSubscribe<Account>() {
            @Override
            public void call(Subscriber<? super Account> subscriber) {

            }
        }).subscribeOn(Schedulers.io());
    }
}
