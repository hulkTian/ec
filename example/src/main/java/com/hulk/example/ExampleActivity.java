package com.hulk.example;

import com.hulk.core.activities.ProxyActivity;
import com.hulk.core.delegates.HulkDelegate;
import com.hulk.ec.launcher.LauncherDelegate;
import com.hulk.ec.launcher.LauncherScrollDelegate;

/**
 * 单activity实现
 */
public class ExampleActivity extends ProxyActivity {

    @Override
    public HulkDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void post(Runnable runnable) {

    }

}
