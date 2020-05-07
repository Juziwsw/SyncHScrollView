package com.mingying.synchscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyulaoshi on 2018/1/31.
 * 自定义的 滚动控件
 * 重载了 {@link SyncHScrollView#onScrollChanged}（滚动条变化）,监听每次的变化通知给观察(此变化的)观察者
 * 可使用 {@link SyncHScrollView#AddOnScrollChangedListener(SyncHScrollView.OnScrollChangedListener) } 来订阅本控件的 滚动条变化
 */

public class SyncHScrollView extends HorizontalScrollView {
    SyncHScrollView.ScrollViewObserver mScrollViewObserver = new SyncHScrollView.ScrollViewObserver();



    public SyncHScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SyncHScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SyncHScrollView(Context context) {
        super(context);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if (mScrollViewObserver != null) {
                    mScrollViewObserver.NotifyGestureListener(MotionEvent.ACTION_DOWN);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                getParent().getParent().requestDisallowInterceptTouchEvent(false);
                if (mScrollViewObserver != null) {
                    mScrollViewObserver.NotifyGestureListener(MotionEvent.ACTION_MOVE);
                }
                break;
            case MotionEvent.ACTION_UP:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                if (mScrollViewObserver != null) {
                    mScrollViewObserver.NotifyGestureListener(MotionEvent.ACTION_UP);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                getParent().requestDisallowInterceptTouchEvent(true);

                if (mScrollViewObserver != null) {
                    mScrollViewObserver.NotifyGestureListener(MotionEvent.ACTION_CANCEL);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        /*
         * 当滚动条移动后，引发 滚动事件。通知给观察者，观察者会传达给其他的条目中的滚动视图。
         */
        if (mScrollViewObserver != null) {
            mScrollViewObserver.NotifyOnScrollChanged(l, t, oldl, oldt);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    /*
     * 订阅 本控件 的 滚动条变化事件
     * */
    public void AddOnScrollChangedListener(SyncHScrollView.OnScrollChangedListener listener) {
        mScrollViewObserver.AddOnScrollChangedListener(listener);
    }

    /*
     * 订阅 本控件 的 滚动条变化事件
     * */
    public void AddOnScrollChangedListener(SyncHScrollView.OnScrollChangedListener listener, SyncHScrollViewGestureListener viewGestureListener) {
        mScrollViewObserver.AddOnScrollChangedListener(listener,viewGestureListener);


    }

    /*
     * 取消 订阅 本控件 的 滚动条变化事件
     * */
    public void RemoveOnScrollChangedListener(SyncHScrollView.OnScrollChangedListener listener) {
        mScrollViewObserver.RemoveOnScrollChangedListener(listener);
    }

    /*
     * 当发生了滚动事件时
     */
    public static interface OnScrollChangedListener {
        public void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    /**
     * 观察者
     */
    public class ScrollViewObserver {
        List<OnScrollChangedListener> mList;
        List<SyncHScrollViewGestureListener> mGestureListeners;

        public ScrollViewObserver() {
            super();
            mList = new ArrayList<>();
            mGestureListeners = new ArrayList<>();
        }

        public void AddOnScrollChangedListener(SyncHScrollView.OnScrollChangedListener listener) {
            mList.add(listener);

        }

        public void AddOnScrollChangedListener(SyncHScrollView.OnScrollChangedListener listener, SyncHScrollViewGestureListener gestureListener) {
            mList.add(listener);
            mGestureListeners.add(gestureListener);

        }

        public void RemoveOnScrollChangedListener(
                SyncHScrollView.OnScrollChangedListener listener) {
            mList.remove(listener);

        }

        public void NotifyOnScrollChanged(int l, int t, int oldl, int oldt) {
            if (mList == null || mList.size() == 0) {
                return;
            }
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i) != null) {
                    mList.get(i).onScrollChanged(l, t, oldl, oldt);
                }
            }
        }

        public void NotifyGestureListener(int action_motion) {
            if (mGestureListeners == null || mGestureListeners.size() == 0) {
                return;
            }
            mGestureListeners.get(0).action(action_motion);
        }
    }
}