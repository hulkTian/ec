package com.hulk.core.delegates;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hulk.core.activities.ProxyActivity;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * 父代理
 * Created by tzh on 2018/8/7.
 */

public abstract class BaseDelegate extends SwipeBackFragment {
    private View mRootView = null;

    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView);

    public <T extends View> T $(@IdRes int viewId) {
        if (mRootView != null) {
            return mRootView.findViewById(viewId);
        }
        throw new NullPointerException("rootView is null");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(),container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }
        mRootView = rootView;
        onBindView(savedInstanceState, rootView);
        return mRootView;
    }

    public final ProxyActivity getProxyActivity() {
        return (ProxyActivity) _mActivity;
    }
}
