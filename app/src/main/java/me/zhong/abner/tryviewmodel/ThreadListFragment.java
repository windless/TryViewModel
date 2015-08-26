package me.zhong.abner.tryviewmodel;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Abner on 15/8/26.
 */
public class ThreadListFragment extends Fragment {
    private ThreadListViewModel _threadListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thread_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        _threadListViewModel = new ThreadListViewModel(getContext(), R.layout.item_thread);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
