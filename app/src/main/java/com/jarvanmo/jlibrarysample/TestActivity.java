package com.jarvanmo.jlibrarysample;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jarvanmo.jlibrary.base.BaseActivity;

/**
 * Created by mo on 16-3-28.
 *
 */
public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
