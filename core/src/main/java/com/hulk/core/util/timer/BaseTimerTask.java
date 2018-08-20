package com.hulk.core.util.timer;

import java.util.TimerTask;

/**
 * Created by hulkTian on 2018/8/19.
 */

public class BaseTimerTask extends TimerTask{
    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
