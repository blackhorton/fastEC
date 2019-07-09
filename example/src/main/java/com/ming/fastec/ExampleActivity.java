package com.ming.fastec;

import com.ming.latte.activity.ProxyActivity;
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
