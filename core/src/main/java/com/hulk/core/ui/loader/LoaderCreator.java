package com.hulk.core.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by tzh on 2018/8/8.
 */

public class LoaderCreator {

    //存放不同类型的Loader,再次使用会直接映射到对应类型
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    /**
     * 创建一种类型的Loader
     */
    static AVLoadingIndicatorView create(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        //未创建当前类型的loader，创建
        if (LOADING_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    /**
     * 创建Indicator
     */
    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        //indicator完整包名
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            //反射indicator类
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            //通过类反射创建实例对象
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
