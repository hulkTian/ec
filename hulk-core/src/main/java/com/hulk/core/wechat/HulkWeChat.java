package com.hulk.core.wechat;

import android.app.Activity;

import com.hulk.core.app.ConfigKeys;
import com.hulk.core.app.Hulk;
import com.hulk.core.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by hulkTian on 2018/8/28.
 */

public class HulkWeChat {
    public static final String APP_ID = Hulk.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Hulk.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback = null;

    private static final class Holder {
        private static final HulkWeChat INSTANCE = new HulkWeChat();
    }

    public static HulkWeChat getInstance() {
        return Holder.INSTANCE;
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    private HulkWeChat() {
        final Activity activity = Hulk.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public HulkWeChat onSignSuccess(IWeChatSignInCallback callback) {
        this.mSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
