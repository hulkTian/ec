package com.hulk.example;

import com.hulk.core.activities.ProxyActivity;
import com.hulk.core.delegates.HulkDelegate;

/**
 * 单activity实现
 */
public class ExampleActivity extends ProxyActivity {

    @Override
    public HulkDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

    @Override
    public void post(Runnable runnable) {

    }

}
