package com.hulk.core.wechat.templates;

import com.hulk.core.wechat.BaseWXEntryActivity;
import com.hulk.core.wechat.HulkWeChat;

/**
 * 微信登录回调类，采用透明方式显示
 * Created by hulkTian on 2018/8/27.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        //登录成功立即关闭，并不适用过场动画
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        HulkWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
