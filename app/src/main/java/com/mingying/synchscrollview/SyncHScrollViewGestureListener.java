package com.mingying.synchscrollview;

/**
 * @author wsw
 * 侧滑手指控制
 */
public interface SyncHScrollViewGestureListener {
    //手指按下
    void actionDown();

    //手指滑动
    void actionMove();

    //手指松开
    void actionCANCEL();

    void action(int action_motion);
}
