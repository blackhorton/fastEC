package com.ming.latte.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.ming.latte.util.timer.ITimerListener;

/**
 * @author Hortons
 * on 2019/8/3
 */


public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {

    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
