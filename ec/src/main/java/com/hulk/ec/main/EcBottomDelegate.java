package com.hulk.ec.main;

import android.graphics.Color;

import com.hulk.core.delegates.bottom.BaseBottomDelegate;
import com.hulk.core.delegates.bottom.BottomItemDelegate;
import com.hulk.core.delegates.bottom.BottomTabBean;
import com.hulk.core.delegates.bottom.ItemBuilder;
import com.hulk.ec.main.cart.ShopCartDelegate;
import com.hulk.ec.main.discover.DiscoverDelegate;
import com.hulk.ec.main.index.IndexDelegate;
import com.hulk.ec.main.personal.PersonalDelegate;
import com.hulk.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by hulkTian on 2018/9/8.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ff8800");
    }
}
