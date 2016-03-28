package com.jarvanmo.jlibrary.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mo on 16-3-28.
 *
 */
public class BaseViewHolder<B extends  ViewDataBinding>  extends RecyclerView.ViewHolder{

    private View itemView;
    private B mBinding ;
    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public B getBinding(){
        return mBinding;
    }
    public void setBinding(B binding){
        mBinding = binding;
    }
}
