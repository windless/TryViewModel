package me.zhong.abner.tryviewmodel;

import rx.Observable;

/**
 * Created by Abner on 15/8/26.
 */
public class Thread {
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Observable<String> getContactName$() {
        return Observable.just("haha");
    }
}

