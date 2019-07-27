package com.ming.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ming.latte.ui.LatteLoader;

/**
 * @author Hortons
 * on 2019/7/27
 */


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        LatteLoader.showLoading(this);
    }
}
