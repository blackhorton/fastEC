package com.ming.fastec;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.ming.latte.app.Latte;
import com.ming.latte.ec.database.DatabaseManager;
import com.ming.latte.ec.icon.FontEcModule;
import com.ming.latte.net.interceptors.DebugInterceptor;

/**
 * @author Ming
 * on 2019/7/4
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withLoaderDelayed(1000)
                .withIcon(new FontEcModule())
                .withIcon(new FontAwesomeModule())
//                .withApiHost("http://127.0.0.1/")
                .withApiHost("http://192.168.1.101/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );
    }
}
