package com.ming.fastec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ming.latte.app.Latte;

/**
 * @author Ming
 * on 2019/7/4
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Latte.init(this);
    }
}
