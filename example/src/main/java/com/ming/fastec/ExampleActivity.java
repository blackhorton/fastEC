package com.ming.fastec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ming.latte.activity.ProxyActivity;
import com.ming.latte.app.Latte;
import com.ming.latte.delegates.LatteDelegate;

/**
 * @author Ming
 * on 2019/7/4
 */
public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
