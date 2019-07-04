package com.ming.latte.app;

import java.util.WeakHashMap;

/**
 * @author Ming
 * on 2019/7/4
 */
public class Configurator {

    /**
     * 存储配置信息
     */
    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    /**
     * 获取Configurator实例
     * @return Configurator
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 获取配置信息的Map
     * @return
     */
    public WeakHashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 执行配置，并设置配置完毕信息
     */
    public final void configure() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    /**
     * 配置Api的Host
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    /**
     * 检查是否配置完毕
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
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
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

    /**
     * 静态内部类得到Configurator的实例
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

}
