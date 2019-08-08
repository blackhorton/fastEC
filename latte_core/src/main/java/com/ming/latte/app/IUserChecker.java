package com.ming.latte.app;

/**
 * @author Hortons
 * on 8/8/19
 */

public interface IUserChecker {
    /**
     * 登录有用户信息
     */
    void onSignIn();

    /**
     * 登录没有用户信息
     */
    void onNotSignIn();
}
