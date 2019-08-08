package com.ming.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ming.latte.delegates.LatteDelegate;
import com.ming.latte.net.RestClient;
import com.ming.latte.net.callback.IError;
import com.ming.latte.net.callback.IFailure;
import com.ming.latte.net.callback.ISuccess;
import com.ming.latte.util.log.LatteLogger;
import com.orhanobut.logger.Logger;

/**
 * @author Hortons
 * on 2019/7/6
 */


public class ExampleDelegate extends LatteDelegate {

    public static final String TAG = ExampleDelegate.class.getSimpleName();

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();

    }

    private void testRestClient() {
        RestClient.builder()
//                .url("https://127.0.0.1/index")
//                .url("https://www.baidu.com")
                .url("http://192.168.1.101/RestService/api/user_profile.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response,Toast.LENGTH_LONG).show();
                        Log.d(TAG, response);
//                        LatteLogger.json("USER_PROFILE", response);
                        Logger.t("USER_PROFILE").json(response);
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
                .build()
                .get();
    }
}
