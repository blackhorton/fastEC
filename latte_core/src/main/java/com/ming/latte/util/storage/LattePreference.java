package com.ming.latte.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ming.latte.app.Latte;


/**
 * @author Hortons
 * on 2019/8/2
 */


public class LattePreference {

    /**
     * 提示：Activity.getPreference(int mode) 生成 Activity名.xml 用于Activity内部存储
     * PreferenceManager.getDefaultSharedPreferences(Context)生成 包名_preferences.xml
     * Context.getSharedPreferences(String name, int mode)生成 name.xml
     */

    private static final SharedPreferences PREFERENCES =
            PreferenceManager.getDefaultSharedPreferences(Latte.getApplicationContext());
    private static final String APP_PREFERENCES_KEY = "profile";

    private static SharedPreferences getAppPreferences() {
        return PREFERENCES;
    }

    public static void setAppProfile(String val) {
        getAppPreferences()
                .edit()
                .putString(APP_PREFERENCES_KEY, val)
                .apply();
    }

    public static String getAppProfile() {
        return getAppPreferences().getString(APP_PREFERENCES_KEY, "null");
    }

    public static JSONObject gettAppProfileJson() {
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    public static void removeAppProfile() {
        getAppPreferences()
                .edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    public static void clearAppPreferences() {
        getAppPreferences()
                .edit()
                .clear()
                .apply();
    }

    /**
     * 可存入是否第一次使用该App的标识
     * @param key
     * @param flag
     */
    public static void setAppFlag(String key, boolean flag) {
        getAppPreferences()
                .edit()
                .putBoolean(key, flag)
                .apply();
    }

    public static boolean getAppFlag(String key) {
        return getAppPreferences().getBoolean(key, false);
    }

    public static void addCustomAppProfile(String key, String val) {
        getAppPreferences()
                .edit()
                .putString(key, val)
                .apply();
    }

    public static String getCustomAppProfile(String key) {
        return getAppPreferences().getString(key, "null");
    }
}
