package com.hulk.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.hulk.core.delegates.bottom.BottomItemDelegate;
import com.hulk.ec.R;

/**
 * Created by hulkTian on 2018/9/8.
 */

public class PersonalDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
