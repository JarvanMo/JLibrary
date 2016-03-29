package com.jarvanmo.jlibrarysample.rc;

import android.content.Context;
import android.view.View;

import com.jarvanmo.jlibrary.base.recycleview.RecycleViewAdapter;
import com.jarvanmo.jlibrarysample.R;
import com.jarvanmo.jlibrarysample.databinding.ItemTestRvBinding;
import com.jarvanmo.jlibrarysample.model.TestModel;

/**
 * Created by mo on 16-3-28.
 *
 */
public class TestAdapter extends  RecycleViewAdapter<ItemTestRvBinding,TestModel, TestHolder>{


    public TestAdapter(Context context) {
        super(context);
    }



    @Override
    public int getLayoutId() {
        return R.layout.item_test_rv;
    }

    @Override
    public void onControlView(final TestModel item, ItemTestRvBinding binding, TestHolder holder, int position) {

        binding.testNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setUserName("hello->" + System.currentTimeMillis());
                notifyDataSetChanged();

            }
        });
    }



//    @Override
//    public void onControlView(TestHolder holder, int position ,TestModel item) {
//        ItemTestRvBinding binding = holder.getBinding();
//        final TestModel testModel = getItem(position);
//        binding.testNameTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                testModel.setUserName("hello->" + System.currentTimeMillis());
//                notifyDataSetChanged();
//
//            }
//        });
//
//    }



}
