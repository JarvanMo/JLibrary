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


        for(int i = 0 ; i < 10 ;i++){
            TestModel testModel = new TestModel();
            testModel.setUserName("name:" + (10 - i));
            if(i%2 == 0){
                testModel.setSex("男");
            }else {
                testModel.setSex("女");
            }

            data.add(testModel);
        }

        adapter = new TestAdapter(this);
        adapter.addAll(data);
        dataBinding.testRv.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.testRv.setAdapter(adapter);

//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for(int i = 0 ; i < 5 ;i++){
//                    TestModel testModel = new TestModel();
//                    testModel.setUserName("test "+ i +" p");
//                    if(i%2 == 0){
//                        testModel.setSex("f女");
//                    }else {
//                        testModel.setSex("m男");
//                    }
//                    adapter.add(testModel);
//                }
//            }
//        },1000);
//
//        final List<TestModel> data1 = new ArrayList<>();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for(int i = 0 ; i < 5 ;i++){
//                    TestModel testModel = new TestModel();
//                    testModel.setUserName("No." + i);
//                    if(i%2 == 0){
//                        testModel.setSex("f");
//                    }else {
//                        testModel.setSex("m");
//                    }
//                    data1.add(testModel);
//                }
//
//                adapter.addAll(data1);
//            }
//        },1500);
    }
}
