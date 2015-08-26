package me.zhong.abner.tryviewmodel;

import rx.Observable;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/**
 * Created by Abner on 15/8/26.
 */
public class ThreadViewModel {
    private PublishSubject<String> _contactName$ = PublishSubject.create();

    private Binder _binder = Binder.create();

    public void bind(Thread thread) {
        _binder.clear();

        _binder.add(thread.getContactName$().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                _contactName$.onNext(s);
            }
        }));
    }

    public Observable<String> getContactName$() {
        return _contactName$.asObservable();
    }
}
