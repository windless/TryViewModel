package me.zhong.abner.tryviewmodel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import java.util.HashSet;
import java.util.Set;

import rx.functions.Action1;
import rx.subjects.PublishSubject;

/**
 * Created by Abner on 15/8/26.
 */
public class ThreadListViewModel extends DataAdapter<Thread, ThreadView> {
    private PublishSubject<Boolean> isLoading      = PublishSubject.create();
    private PublishSubject<Thread>  threadToDetail = PublishSubject.create();
    private PublishSubject<Boolean> isLoadingMore  = PublishSubject.create();

    private Set<Thread> selectedThreads = new HashSet<>();
    private boolean _isEditMode;

    public ThreadListViewModel(Context context, int resId) {
        super(context, resId);
    }

    public void bindListView(ListView listView) {
        listView.setAdapter(this);
        listView.setOnItemClickListener(this);
    }

    public void refresh() {

    }

    public void loadMore() {

    }

    public void setSearchText(String searchText) {

    }

    public void setIsEditMode(boolean isEditMode) {
        _isEditMode = isEditMode;
        if (!isEditMode) {
            selectedThreads.clear();
        }
        notifyDataSetChanged();
    }

    @Override
    void bind(Thread thread, final ThreadView view, Binder binder) {
        ThreadViewModel threadViewModel = (ThreadViewModel) view.getTag();
        if (threadViewModel == null) {
            threadViewModel = new ThreadViewModel();
            threadViewModel.getContactName$().subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                }
            });
            view.setTag(threadViewModel);
        }
        threadViewModel.bind(thread);

        binder.add(thread.getContactName$().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
            }
        }));

//        view.getSubjectText().setText(thread.getSubject());
//        view.setSelected(selectedThreads.contains(thread));
//        view.setSelectable(_isEditMode);
    }

    @Override
    void onClick(Thread thread) {
        if (_isEditMode) {
            toggleThreadSelection(thread);
        }
    }

    private void toggleThreadSelection(Thread thread) {
        if (selectedThreads.contains(thread)) {
            selectedThreads.remove(thread);
        } else {
            selectedThreads.add(thread);
        }
        notifyDataSetChanged();
    }

    public abstract class ThreadViewable extends View {
        public ThreadViewable(Context context) {
            super(context);
        }

        public ThreadViewable(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        abstract void setSubject(String subject);

        abstract void setContactName(String contactName);
    }
}
