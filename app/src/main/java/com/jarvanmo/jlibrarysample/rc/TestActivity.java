package com.jarvanmo.jlibrarysample.rc;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.jarvanmo.jlibrary.base.context.BaseActivity;
import com.jarvanmo.jlibrarysample.R;
import com.jarvanmo.jlibrarysample.databinding.ActivityTestBinding;
import com.jarvanmo.jlibrarysample.model.TestModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mo on 16-3-28.
 *
 */
public class TestActivity extends BaseActivity {
    List<TestModel> data = new ArrayList<>();
    TestAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityTestBinding dataBinding =  DataBindingUtil.setContentView(this, R.layout.activity_test);


        for(int i = 0 ; i < 20 ;i++){
            TestModel testModel = new TestModel();
            testModel.setUserName(System.currentTimeMillis()+"");
            testModel.setSex("男");
            data.add(testModel);
        }

        adapter = new TestAdapter(this,data);
        dataBinding.testRv.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.testRv.setAdapter(adapter);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 20 ;i++){
                    TestModel testModel = new TestModel();
                    testModel.setUserName(System.currentTimeMillis()+"");
                    testModel.setSex("女");
                    adapter.add(testModel);
                }
            }
        },1000);
    }
}
