package com.hulk.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.hulk.core.app.Hulk;
import com.hulk.core.delegates.HulkDelegate;
import com.hulk.core.net.RestClient;
import com.hulk.core.net.callback.IError;
import com.hulk.core.net.callback.IFailure;
import com.hulk.core.net.callback.IRequest;
import com.hulk.core.net.callback.ISuccess;

/**
 * 项目具体实现的delegate
 * Created by tzh on 2018/8/7.
 */

public class ExampleDelegate extends HulkDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("https://hibingoimg-1253225888.cosgz.myqcloud.com/android/hibingo.apk")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                    }
                })
                .dir("hibingo")
                .extension("apk")
                .build()
                .download();
    }
}
