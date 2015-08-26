package me.zhong.abner.tryviewmodel;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Abner on 15/8/26.
 */
public class ThreadView extends LinearLayout {
    private boolean _selectable;

    public ThreadView(Context context) {
        super(context);
    }

    public ThreadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView getSubjectText() {
        return null;
    }

    public void setSelectable(boolean selectable) {
        _selectable = selectable;
    }
}
