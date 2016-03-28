package com.jarvanmo.jlibrarysample.rc;

import android.content.Context;
import android.support.annotation.LayoutRes;

import com.jarvanmo.jlibrary.base.RecycleViewAdapter;
import com.jarvanmo.jlibrarysample.model.TestModel;

import java.util.List;

/**
 * Created by mo on 16-3-28.
 *
 */
public class TestAdapter extends  RecycleViewAdapter<TestModel, TestHolder>{
    public TestAdapter(Context context, List<TestModel> data, @LayoutRes int layoutResId, Class<TestHolder> clazz) {
        super(context, data, layoutResId, clazz);
    }


    @Override
    public void onControlView(TestHolder holder, int position) {
//        ItemTestRvBinding binding = holder.getBinding();
//        TestModel model = getItem(position);
//        //because TestModel extends BaseObservable　，so we can do this directly
//        binding.setTestModel(model);

        //or we can do
//        binding.setVariable(BR.testModel,model);
//        holder.getBinding().executePendingBindings();//立即更新UI

//        final TestHolder item = mItems.get(position);
//        holder.getBinding().setVariable(BR.item, item);
//        holder.getBinding().executePendingBindings();
    }
}
