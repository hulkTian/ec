package com.hulk.core.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by tzh on 2018/8/6.
 */

public final class Hulk {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getHulkConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Application getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }
}
