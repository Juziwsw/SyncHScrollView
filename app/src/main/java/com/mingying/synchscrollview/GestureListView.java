package com.mingying.synchscrollview;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @author wsw
 */
public class GestureListView extends ListView {
    private GestureDetector gestureDetector;
    private Gesture gesture;

    public GestureListView(Context context) {
        super(context);
        gesture = new Gesture(context);
        gestureDetector = new GestureDetector(context, gesture);
        setSelector(R.color.transparent);
    }

    public GestureListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gesture = new Gesture(context);
        gestureDetector = new GestureDetector(context, gesture);
        setSelector(R.color.transparent);
    }

    public GestureListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gesture = new Gesture(context);
        gestureDetector = new GestureDetector(context, gesture);
        setSelector(R.color.transparent);
    }


    @Override
    public void setSelector(int resID) {
        super.setSelector(R.color.transparent);
        setDivider(new ColorDrawable(getResources().getColor(R.color.color_E6E6E6)));
        setDividerHeight(1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (positionListener != null) {
            positionListener.fingerPosition(ExtKt.px2dp(getContext(), ev.getX()), ExtKt.px2dp(getContext(), ev.getY()));
        }
        if (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_CANCEL) {
            if (scrollInListViewListener != null) {
                scrollInListViewListener.actionUp();
            }
        }

        if (gestureDetector.onTouchEvent(ev)) {
            if (scrollInListViewListener != null) {
                scrollInListViewListener.scrollIn(true);
            }
            return true;
        }
        if (scrollInListViewListener != null) {
            scrollInListViewListener.scrollIn(false);
        }

        return super.onTouchEvent(ev);
    }

    private ScrollInListViewListener scrollInListViewListener;
    private FingerPositionListener positionListener;

    public void setAdapter(ListAdapter adapter, ScrollInListViewListener scrollInListViewListener) {
        super.setAdapter(adapter);
        this.scrollInListViewListener = scrollInListViewListener;
    }

    public void setAdapter(ListAdapter adapter, ScrollInListViewListener scrollInListViewListener, FingerPositionListener positionListener) {
        super.setAdapter(adapter);
        this.scrollInListViewListener = scrollInListViewListener;
        this.positionListener = positionListener;
    }


    public boolean getisSlideAndDown() {
        return gesture.getisSlideAndDown();
    }

    public boolean getIsSlide() {
        return gesture.getIsSlide();
    }

}
