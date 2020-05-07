package com.mingying.synchscrollview;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * @author wsw
 */
public class Gesture implements GestureDetector.OnGestureListener {
    Context context;
    //左右滑动标识
    private boolean isStart = false;
    //上下活动标识
    private boolean isSlideAndDown = false;
    private int count;


    public Gesture(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }


    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    /**
     * 滑动中
     *
     * @param e1
     * @param e2
     * @param distanceX
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (isStart) {
            return true;
        }
        if (isSlideAndDown) {
            return false;
        }
        //Log.e("MYTAG2", "e1.x==" + e1.getX() + "---e2.x==" + e2.getX() + "---e1.y==" + e1.getY() + "---e2.y" + e2.getY() + "---" + distanceX + "---" + distanceY);
        if (e1.getX() - e2.getX() > 50 || Math.abs(distanceX) > Math.abs(distanceY)) {
            Log.i("MYTAG", "向左滑...");
            isStart = true;
            return true;
        }
        if (e2.getX() - e1.getX() > 50 || Math.abs(distanceX) > Math.abs(distanceY)) {
            Log.i("MYTAG", "向右滑...");
            isStart = true;
            return true;
        }
        if (e1.getY() - e2.getY() > 50) {
            Log.i("MYTAG", "向上滑...");
            isSlideAndDown = true;
            return false;
        }
        if (e2.getY() - e1.getY() > 50) {
            Log.i("MYTAG", "向下滑...");
            isSlideAndDown = true;
            return false;
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    /**
     * 起手时,只有一次,动作过小,不触发
     *
     * @param e1
     * @param e2
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (isStart) {
            isStart = false;
            return true;
        }
        if (isSlideAndDown) {
            isSlideAndDown = false;
            return false;
        }
        if (e1.getX() - e2.getX() > 50) {
            Log.i("MYTAG1", "向左滑...");

            return true;
        }
        if (e2.getX() - e1.getX() > 50) {
            Log.i("MYTAG1", "向右滑...");
            return true;
        }
        if (e1.getY() - e2.getY() > 50) {
            Log.i("MYTAG1", "向上滑...");
            return false;
        }
        if (e2.getY() - e1.getY() > 50) {
            Log.i("MYTAG1", "向下滑...");
            return false;
        }
        return false;
    }

    public boolean getisSlideAndDown() {
        return isSlideAndDown;
    }

    public boolean getIsSlide(){
        return isSlideAndDown || isStart;
    }

    public void  setValue(){
        isStart = false;
        isSlideAndDown = false;
    }
}
