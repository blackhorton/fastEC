package com.ming.latte.app;

import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * @author Ming
 * on 2019/7/4
 */
public class Configurator {

    /**
     * 存储配置信息
     * 键值KEY使用Object类型可以使KEY的类型更加的灵活，可以传入int、Enum、String类型
     */
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();

    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private static final Handler HANDLER = new Handler();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    /**
     * 获取Configurator实例
     * @return Configurator
     */
    public static Configurator getInstance() {
        return ConfiguratorHolder.INSTANCE;
    }

    /**
     * 获取配置信息的Map
     * @return
     */
    public HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 执行配置，并设置配置完毕信息
     */
    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    /**
     * 配置Api的Host
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    /**
     * 检查是否配置完毕
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    /**
     * 查看配置信息
     * @param key   查询的配置的名称
     * @param <T>   配置信息
     * @return      返回配置信息
     */
    @SuppressWarnings("unchecked")

    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + "IS NULL");
        }
        return (T) value;
    }

    public final Configurator withLoaderDelayed(long delayed) {
        LATTE_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }
    public final Configurator withInterceptor(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 静态内部类得到Configurator的实例
     */
    private static class ConfiguratorHolder {
        private static final Configurator INSTANCE = new Configurator();
    }

}
