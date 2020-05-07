package com.mingying.synchscrollview;

import android.content.Context;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @author wsw
 */
public class InterceptScrollContainer extends LinearLayout {
    public InterceptScrollContainer(Context context) {
        super(context);
    }

    public InterceptScrollContainer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptScrollContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 拦截TouchEvent
     *
     * @see
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }


}
