package com.ming.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.ming.latte.activity.ProxyActivity;
import com.ming.latte.delegates.LatteDelegate;
import com.ming.latte.ec.sign.SignInDelegate;
import com.ming.latte.ec.sign.SignUpDelegate;

/**
 * @author Ming
 * on 2019/7/4
 */
public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
//        return new ExampleDelegate();
//        return new LauncherDelegate();
//        return new LauncherScrollDelegate();
        return new SignUpDelegate();
//        return new SignInDelegate();
    }
}
