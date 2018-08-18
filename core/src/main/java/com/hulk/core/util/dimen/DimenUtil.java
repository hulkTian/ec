package com.hulk.core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.hulk.core.app.Hulk;

/**
 * Created by tzh on 2018/8/8.
 */

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Hulk.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Hulk.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
