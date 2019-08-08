package com.ming.latte.util.dimen;

/**
 * @author Hortons
 * on 2019/7/26
 */

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.ming.latte.app.Latte;

/**
 * 计算屏幕的长宽高
 */
public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
