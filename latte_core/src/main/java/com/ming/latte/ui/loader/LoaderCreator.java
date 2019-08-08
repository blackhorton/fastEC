package com.ming.latte.ui.loader;

/**
 * @author Hortons
 * on 2019/7/26
 */

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * AVLoadingIndicatorView 官方是通过反射机制来获取Loader的名字加载，对性能方面会有影响
 * 以缓存的方式创建Loader，避免每一次使用Loader时都需要使用反射机制加载，提高应用性能
 */
public final class LoaderCreator {

    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    /**
     * 修改setIndicator(String indicatorName)方法，使其通用
     */
    private static Indicator getIndicator(String name) {
        //TextUtils.isEmpty(indicatorName)
        if (name == null || name.isEmpty()) {
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        //如果名字中包含"."则说明传入的是整一个类名，否则则不包括后缀
        if (!name.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
