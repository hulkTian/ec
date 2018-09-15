package com.hulk.core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hulk.core.app.Hulk;
import com.hulk.core.net.RestClient;
import com.hulk.core.ui.recycler.DataConverter;
import com.hulk.core.ui.recycler.MultipleRecyclerAdapter;
import com.hulk.core.util.HulkLogger;

/**
 * Created by hulkTian on 2018/9/15.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener
, BaseQuickAdapter.RequestLoadMoreListener{
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;

    private RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                           RecyclerView recyclerView, DataConverter converter, PagingBean bean) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView, DataConverter converter) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, converter, new PagingBean());
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Hulk.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    public void firstPage(String url) {
        HulkLogger.d("IUDHAS", url);
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(response -> {
                    final JSONObject object = JSON.parseObject(response);
                    BEAN.setTotal(object.getInteger("total"))
                            .setPageSize(object.getInteger("page_size"));
                    //设置Adapter
                    mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                    mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                    RECYCLERVIEW.setAdapter(mAdapter);
                    BEAN.addIndex();
                })
                .build()
                .get();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
