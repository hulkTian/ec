package com.hulk.example;

import android.app.Application;

import com.hulk.core.app.Hulk;
import com.hulk.core.net.interceptors.DebugInterceptor;
import com.hulk.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by tzh on 2018/8/6.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化全局配置项
        Hulk.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withApiHost("http://114.67.235.114/RestServer/api/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .configure();
    }
}
