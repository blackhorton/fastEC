package com.ming.latte.app;

import com.ming.latte.util.storage.LattePreference;

/**
 * @author Hortons
 * on 8/8/19
 * 用户管理类
 */


public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    /**
     * 保存用户登录状态，登陆后调用
     * @param state 状态值
     */
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }


    /**
     * 判断是否有登录过
     * @return
     */
    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }


}
