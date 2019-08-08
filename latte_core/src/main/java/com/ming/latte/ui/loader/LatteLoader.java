package com.ming.latte.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.ming.latte.R;
import com.ming.latte.util.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * @author Hortons
 * on 2019/7/26
 */


public class LatteLoader {

    //缩放大小
    private static final int LOADER_SIZE_SCALE = 8;

    //根据屏幕上下的偏移量
    private static final int LOADER_OFFSET_SCALE = 10;

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    /**
     * 创建管理所有Loader的集合
     * 便于统一的创建和关闭Loader，省下同步操作
     */
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }

    public static void showLoading(Context context, String type) {
        //Dialog承载Loader并且能够弹出来
        //尽量使用v7当中的类，会使应用更加兼容
        //注意：此处的AppCompatDialog使用自定义主题
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    /**
     * @param context 传入布局相对应的Context，否者可能会报错
     */
    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    //dialog.cancel()会执行onCancel()回调方法,dialog.dismiss()只会消失
                    dialog.cancel();
                }
            }
        }
    }


}
