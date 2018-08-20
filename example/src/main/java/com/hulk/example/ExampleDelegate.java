package com.hulk.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.hulk.core.app.Hulk;
import com.hulk.core.delegates.HulkDelegate;
import com.hulk.core.net.RestClient;
import com.hulk.core.net.RestCreator;
import com.hulk.core.net.callback.IError;
import com.hulk.core.net.callback.IFailure;
import com.hulk.core.net.callback.ISuccess;
import com.hulk.core.net.rx.RxRestClient;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
//        testRestClient();
//        testRxRestClient();
//        testRxRestClient_2();
    }

    //TODO 测试RestClient
    private void testRestClient() {
        RestClient.builder()
                .url("http://127.0.0.1/test")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Hulk.getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(Hulk.getApplicationContext(), "download failure", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                    }
                })
                .build()
                .get();
    }

    //TODO 测试Rxjava
    private void testRxRestClient() {
        String url = "http://www.baidu.com";
        WeakHashMap<String, Object> params = new WeakHashMap<>();
        Observable<String> observable = RestCreator.getRxRestService().get(url, params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(Hulk.getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO 测试Rxjava
    private void testRxRestClient_2() {
        RxRestClient.builder()
                .url("http://www.baidu.com")
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(Hulk.getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
