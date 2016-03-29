package com.jarvanmo.jlibrary.base.recycleview;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mo on 16-3-28.
 *How to make it user?
 * I suppose if i don't want to do anything in my holder,
 * how to use BaseViewHolder directly?
 */
public class BaseViewHolder<B extends  ViewDataBinding>  extends RecyclerView.ViewHolder{

    private B mBinding ;
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public B getBinding(){
        return mBinding;
    }
    public void setBinding(B binding){
        mBinding = binding;
    }

}
