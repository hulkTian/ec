package com.hulk.core.net.callback;

/**
 * 请求过程回调接口
 * Created by tzh on 2018/8/8.
 */

public interface IRequest {

    void onRequestStart();

    void onRequestEnd();
}
