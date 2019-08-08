package com.ming.latte.util.timer;

import java.util.TimerTask;

/**
 * @author Hortons
 * on 2019/8/2
 */


public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;


    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        //凡是在传入接口的地方都需要检测接口是否为null，否则会有比较多的麻烦
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
