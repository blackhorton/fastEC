package com.ming.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.ming.latte.delegates.LatteDelegate;
import com.ming.latte.ec.R;
import com.ming.latte.ui.launcher.LauncherHolderCreator;
import com.ming.latte.ui.launcher.ScrollLauncherTag;
import com.ming.latte.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * @author Hortons
 * on 2019/8/3
 */


public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    //传入资源文件
    private ConvenientBanner<Integer> convenientBanner = null;

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        convenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public Object setLayout() {
        convenientBanner = new ConvenientBanner<>(getContext());
        return convenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否已经登录
        }
    }
}
