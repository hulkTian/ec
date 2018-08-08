package com.hulk.core.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;

import com.hulk.core.R;
import com.hulk.core.delegates.HulkDelegate;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 单activity容器
 * Created by tzh on 2018/8/7.
 */

public abstract class ProxyActivity extends AppCompatActivity implements ISupportActivity {
    //Fragmentation 代理类
    private final SupportActivityDelegate DELEGATE = new SupportActivityDelegate(this);
    //加入Fragment
    public abstract HulkDelegate setRootDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DELEGATE.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    /**
     * 初始化布局
     */
    private void initContainer(@Nullable Bundle savedInstanceState) {
        @SuppressLint("RestrictedApi")
        //fragment容器布局
        final ContentFrameLayout container = new ContentFrameLayout(this);
        //设置容器布局id
        container.setId(R.id.delegate_container);
        //将容器布局添加到单activity容器类中
        setContentView(container);
        //加载根fragment
        if (savedInstanceState == null) {
            DELEGATE.loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        DELEGATE.onDestroy();
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return DELEGATE;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return DELEGATE.extraTransaction();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return DELEGATE.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        DELEGATE.setFragmentAnimator(new DefaultHorizontalAnimator());
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return DELEGATE.onCreateFragmentAnimator();
    }

    @Override
    public void onBackPressedSupport() {
        DELEGATE.onBackPressedSupport();
    }

    @Override
    public void onBackPressed() {
        DELEGATE.onBackPressed();
    }

}
