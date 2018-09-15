package com.hulk.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.hulk.core.delegates.HulkDelegate;
import com.hulk.core.ui.recycler.MultipleFields;
import com.hulk.core.ui.recycler.MultipleItemEntity;
import com.hulk.ec.main.detail.GoodsDetailDelegate;

/**
 * Created by hulkTian on 2018/9/15.
 */

public class IndexItemClickListener extends SimpleClickListener {
    private final HulkDelegate DELEGATE;

    private IndexItemClickListener(HulkDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(HulkDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final int goodsId = entity.getField(MultipleFields.ID);
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(goodsId);
        DELEGATE.getSupportDelegate().start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
