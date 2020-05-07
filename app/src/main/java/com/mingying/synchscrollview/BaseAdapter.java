package com.mingying.synchscrollview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author wsw
 */
public abstract class BaseAdapter<T> extends CommonAdapter<T> {
    public Context context;
    private GestureListView listView;
    private SyncHScrollView mHeadStickyHSView;
    private int lnew;
    private int tnew;
    private SyncHScrollViewGestureListener gestureListener;

    public BaseAdapter(Context context, GestureListView listView, SyncHScrollView mHeadStickyHSView) {
        this.context = context;
        this.listView = listView;
        this.mHeadStickyHSView = mHeadStickyHSView;
        listView.setOnTouchListener(ListViewAndHeadViewTouchListener);
        mHeadStickyHSView.setOnTouchListener(ListViewAndHeadViewTouchListener);
        lnew = 0;
        tnew = 0;
    }

    public BaseAdapter(Context context, GestureListView listView, SyncHScrollView mHeadStickyHSView, SyncHScrollViewGestureListener gestureListener) {
        this.context = context;
        this.listView = listView;
        this.mHeadStickyHSView = mHeadStickyHSView;
        listView.setOnTouchListener(ListViewAndHeadViewTouchListener);
        mHeadStickyHSView.setOnTouchListener(ListViewAndHeadViewTouchListener);
        this.gestureListener = gestureListener;
        //listView.setScrollInListViewListener(this);
        lnew = 0;
        tnew = 0;
    }

   /* private boolean isEnabled = true;

    @Override
    public void scrollIn(boolean is) {
        if (is && !isActionUp) {
            isEnabled = false;
            notifyDataSetChanged();
        }

    }

    @Override
    public void actionUp() {
        isEnabled = true;
        isActionUp = true;
        notifyDataSetChanged();
        LogUtil.e("---------------scrollIn");
    }

    @Override
    public void actionDown() {
        isActionUp = false;
    }*/


    public abstract class BaseViewHolderList {
        @BindView(R.id.horizontalScrollView)
        SyncHScrollView scrollView;

        public BaseViewHolderList(View view) {
            ButterKnife.bind(this, view);
            if (gestureListener != null) {
                mHeadStickyHSView.AddOnScrollChangedListener(new OnScrollChangedListenerImp(scrollView), gestureListener);
            } else {
                mHeadStickyHSView.AddOnScrollChangedListener(new OnScrollChangedListenerImp(scrollView));
            }
            mHeadStickyHSView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scrollView.smoothScrollTo(lnew, tnew);
                }
            }, 0);

        }
    }

    public class OnScrollChangedListenerImp implements SyncHScrollView.OnScrollChangedListener {
        private SyncHScrollView mScrollViewArg;

        public OnScrollChangedListenerImp(SyncHScrollView mScrollViewArg) {
            this.mScrollViewArg = mScrollViewArg;
        }

        @Override
        public void onScrollChanged(final int l, final int t, int oldl, int oldt) {
            mScrollViewArg.smoothScrollTo(l, t);
            if (gestureListener != null) {
                gestureListener.actionMove();
            }

            lnew = l;
            tnew = t;

        }
    }


    /**
     * TODO 划重点：用来将头部和列表上面的触摸事件都分发给头部的滑动控件
     */
    View.OnTouchListener ListViewAndHeadViewTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // 当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
            if (listView.getisSlideAndDown()) {
                return false;
            }
            mHeadStickyHSView.onTouchEvent(event);
            return false;
        }
    };
}
