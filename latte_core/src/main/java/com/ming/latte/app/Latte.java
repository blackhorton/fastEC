package com.ming.latte.app;

import android.content.Context;
import android.os.Handler;

import java.util.WeakHashMap;

/**
 * @author Ming
 * on 2019/7/4
 *
 * 负责全局变量及参数的初始化及获取
 */

public final class Latte {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }

    public static void test() {
    }
}
