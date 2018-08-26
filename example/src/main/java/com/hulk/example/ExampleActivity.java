package com.hulk.example;

import android.widget.Toast;

import com.hulk.core.activities.ProxyActivity;
import com.hulk.core.delegates.HulkDelegate;
import com.hulk.ec.launcher.ILauncherListener;
import com.hulk.ec.launcher.LauncherDelegate;
import com.hulk.ec.launcher.OnLauncherFinishTag;
import com.hulk.ec.sign.ISignListener;
import com.hulk.ec.sign.SignInDelegate;

/**
 * 单activity实现
 */
public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener{

    @Override
    public HulkDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
//                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
