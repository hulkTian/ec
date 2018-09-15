package com.hulk.core.net.rx;

import android.content.Context;

import com.hulk.core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by tzh on 2018/8/8.
 */

public class RxRestClientBuilder {
    private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private String mUrl;
    private RequestBody mBody;
    private File mFile;
    private Context mContext;
    private LoaderStyle mLoaderStyle;

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    /**
     * 构造者模式，仅供RxRestClient调用
     */
    public final RxRestClient build() {
        return new RxRestClient(mUrl, PARAMS, mBody, mFile, mContext, mLoaderStyle);
    }

}
