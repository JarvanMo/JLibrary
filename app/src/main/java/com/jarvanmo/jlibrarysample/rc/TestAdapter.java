package com.jarvanmo.jlibrarysample.rc;

import android.content.Context;
import android.view.View;

import com.jarvanmo.jlibrary.base.recycleview.RecycleViewAdapter;
import com.jarvanmo.jlibrary.widget.toast.JToast;
import com.jarvanmo.jlibrarysample.R;
import com.jarvanmo.jlibrarysample.databinding.ItemTestRvBinding;
import com.jarvanmo.jlibrarysample.model.TestModel;

import java.util.List;

/**
 * Created by mo on 16-3-28.
 *
 */
public class TestAdapter extends  RecycleViewAdapter<TestModel, TestHolder>{


    public TestAdapter(Context context, List<TestModel> data) {
        super(context, data);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_test_rv;
    }


    @Override
    public Class<TestHolder> getViewHolderType() {
        return TestHolder.class;
    }

    @Override
    public void onControlView(TestHolder holder, int position) {
        ItemTestRvBinding binding = holder.getBinding();
        final TestModel testModel = getItem(position);
        binding.testNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testModel.setUserName("hello->" + System.currentTimeMillis());
                notifyDataSetChanged();

            }
        });

    }



}
