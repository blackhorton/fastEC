package com.ming.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ming.latte.delegates.LatteDelegate;

/**
 * @author Hortons
 * on 2019/7/6
 */


public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
