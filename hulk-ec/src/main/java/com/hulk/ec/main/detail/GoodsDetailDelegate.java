package com.hulk.ec.main.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.hulk.core.delegates.HulkDelegate;
import com.hulk.ec.R;

/**
 * Created by hulkTian on 2018/9/15.
 */

public class GoodsDetailDelegate extends HulkDelegate {
    private static final String ARG_GOODS_ID = "ARG_GOODS_ID";
    private int mGoodsId = -1;

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
    }

    public static GoodsDetailDelegate create(int goodsId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID, goodsId);
        final GoodsDetailDelegate delegate = new GoodsDetailDelegate();
        delegate.setArguments(args);
        return delegate;
    }
}
