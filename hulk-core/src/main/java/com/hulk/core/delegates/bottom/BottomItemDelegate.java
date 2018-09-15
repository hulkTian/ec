package com.hulk.core.delegates.bottom;

import android.view.View;
import android.widget.Toast;

import com.hulk.core.R;
import com.hulk.core.app.Hulk;
import com.hulk.core.delegates.HulkDelegate;

/**
 * Created by hulkTian on 2018/9/8.
 */

public abstract class BottomItemDelegate extends HulkDelegate {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Hulk.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
