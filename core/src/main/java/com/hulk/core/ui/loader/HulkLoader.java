package com.hulk.core.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.hulk.core.R;
import com.hulk.core.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by tzh on 2018/8/8.
 */

public class HulkLoader {
    //loader缩放率
    private static final int LOADER_SIZE_SCALE = 8;
    //loader偏移量
    private static final int LOADER_OFFSET_SCALE = 10;
    //dialog缓存容器，方便管理
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    //默认loader
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    /**
     * 构建loader
     */
    private static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);
        //计算屏幕宽高
        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();
        //获取屏幕对象
        final Window dialogWindow = dialog.getWindow();
        //定义loader位置
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
     * 显示特定样式loader
     */
    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }

    /**
     * 显示默认loader
     */
    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    /**
     * 关闭所有loader
     */
    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }

}
