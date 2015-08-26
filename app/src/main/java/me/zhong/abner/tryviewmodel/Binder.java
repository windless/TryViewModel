package me.zhong.abner.tryviewmodel;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Abner on 15/8/26.
 */
public class Binder {
    private CompositeSubscription _subs = new CompositeSubscription();

    public static Binder create() {
        return new Binder();
    }

    private Binder() {

    }

    public void add(Subscription subscription) {
        _subs.add(subscription);
    }

    public void remove(Subscription subscription) {
        _subs.remove(subscription);
    }

    public void clear() {
        _subs.clear();
    }
}
