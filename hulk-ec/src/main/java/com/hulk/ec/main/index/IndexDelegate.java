package com.hulk.ec.main.index;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.hulk.core.delegates.bottom.BottomItemDelegate;
import com.hulk.core.ui.recycler.BaseDecoration;
import com.hulk.core.ui.refresh.RefreshHandler;
import com.hulk.ec.R;
import com.hulk.ec.main.EcBottomDelegate;

/**
 * Created by hulkTian on 2018/9/8.
 */

public class IndexDelegate extends BottomItemDelegate {
    private RecyclerView mRecyclerView = null;
    private SwipeRefreshLayout mRefreshLayout = null;

    private RefreshHandler mRefreshHandler = null;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRecyclerView = $(R.id.rv_index);
        mRefreshLayout = $(R.id.srl_index);
        final AppCompatEditText mSearchView = $(R.id.et_search_view);

        $(R.id.icon_index_scan).setOnClickListener(view -> startScanWithCheck(getParentDelegate()));
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index.php");
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        final Context context = getContext();
        mRecyclerView.setLayoutManager(manager);
        if (context != null) {
            mRecyclerView.addItemDecoration
                    (BaseDecoration.create(ContextCompat.getColor(context, R.color.app_background), 5));
        }
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        //这里需要使用父类Delegate，不然就是内部Delegate间的切换，底部的bottom布局会保持存在
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }
}
