package com.hulk.core.app;

import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 *
 * Created by tzh on 2018/8/6.
 */

public class Configurator {
    //全局参数容器
    private static final HashMap<Object, Object> HULK_CONFIGS = new HashMap<>();
    //字体图标集
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    //网络请求拦截器
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    //
    private static final Handler HANDLER = new Handler();

    private Configurator() {
        HULK_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        HULK_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final HashMap<Object, Object> getHulkConfigs() {
        return HULK_CONFIGS;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        initIcons();
        HULK_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    /**
     * 添加单个拦截器
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        HULK_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 批量添加拦截器
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        HULK_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 配置主地址
     */
    public final Configurator withApiHost(String host) {
        HULK_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    /**
     * 初始化字体图标库
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 加入自定义字体图标库
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * loader延迟时间
     */
    public final Configurator withLoaderDelayed(long delayed) {
        HULK_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    /**
     * 在获取配置项时，检测是否配置完成
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) HULK_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    /**
     * 获取配置项
     * @param key 配置项键名
     * @param <T> 配置项泛型
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = HULK_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) HULK_CONFIGS.get(key);
    }
}
