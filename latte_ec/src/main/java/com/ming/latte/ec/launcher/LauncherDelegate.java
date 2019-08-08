package com.ming.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ming.latte.delegates.LatteDelegate;
import com.ming.latte.ec.R;
import com.ming.latte.ec.R2;
import com.ming.latte.ui.launcher.ScrollLauncherTag;
import com.ming.latte.util.storage.LattePreference;
import com.ming.latte.util.timer.BaseTimerTask;
import com.ming.latte.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * @author Hortons
 * on 2019/8/2
 */


public class LauncherDelegate extends LatteDelegate implements ITimerListener {


    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;
    Unbinder unbinder;
    private Timer mTimer = null;
    /**
     * 倒计时时间总数
     */
    private int mCount = 5;

    @OnClick(R2.id.tv_launcher_timer)
    public void onClick() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    /**
     * 初始化倒计时
     */
    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 判断是否显示滑动启动页
     */
    private void checkIsShowScroll() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //TODO 检查用户是否登录了APP
        }
    }


    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    //注意此处的pattern
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
