package com.jarvanmo.jlibrary.base.recycleview;

import android.annotation.TargetApi;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by mo on 16-3-28.
 * My goal is to make creating adapter  easier.
 *So,this adapter is based on {@link DataBindingUtil} and reflection.
 * I never test performance of this adapter
 * if you really concern about performance, you'd better not use this adapter.
 *
 */
public abstract class RecycleViewAdapter<B extends ViewDataBinding, M, VH extends BaseViewHolder<B>> extends RecyclerView.Adapter<VH> {

    private List<M> data;


    private Context mContext;

    Constructor<VH> constructor = null;

    @SuppressWarnings("unchecked")
    public RecycleViewAdapter(Context context) {
        this.mContext = context;
        data = new ArrayList<>();

//        Class<VH> clazz = getViewHolderType();
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Class<VH> clazz  = (Class) params[2];
        try {
            constructor = clazz.getDeclaredConstructor(View.class);
            constructor.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    public M getItem(int position) {
        if (position < data.size()) {
            return data.get(position);
        } else {
            return null;
        }
    }

    public Context getContext() {
        return mContext;
    }

    public List<M> getData() {
        return data;
    }


    /**
     * @return return the last one of  data
     * if the size of data is 0 or data is null
     * it will return null
     **/
    public M getLastItem() {

        M result = null;
        if (data != null) {
            int size = data.size();

            if (size > 0) {
                result = data.get(size - 1);
            }
        }

        return result;

    }


    /**
     * reset the data used by adapter
     */
    public void setData(List<M> newData) {
        data.clear();
        addAll(newData);
    }

    /**
     * add a piece of data to the adapter
     *
     * @param item a piece of data
     */
    public void addItem(M item) {

        if (data != null) {
            data.add(item);
            notifyDataSetChanged();
        }
    }

    /**
     * add more add to adapter
     *
     * @param moreData the data need to show together
     */
    public void addAll(List<M> moreData) {

        if (data != null) {
            data.addAll(moreData);
            notifyDataSetChanged();
        }
    }

    /**
     * add a item to the data at index
     **/
    public void add(int position, M item) {

        if (data != null) {
            data.add(position, item);
            notifyDataSetChanged();
        }
    }

    public void add(M item) {
        if (data != null) {
            data.add(item);
            notifyDataSetChanged();
        }
    }


    /**
     * add a item to the data at the first position
     **/
    public void addToFirst(M item) {
        add(0, item);
    }

    public void set(int position, M item) {

        if (data != null) {
            data.set(position, item);
            notifyDataSetChanged();
        }
    }

    public void removeItem(int position) {

        int size = data.size();
        if (position < size) {
            data.remove(position);
            notifyDataSetChanged();
        } else {
            throw new IndexOutOfBoundsException("Invalid index " + position + ", size is " + size);
        }
    }

    public void removeItem(M item) {

        if (data.contains(item)) {
            data.remove(item);
        }
    }

    public void clear() {

        if (data != null) {
            data.clear();
            notifyDataSetChanged();
        }

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("unchecked")
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH vh = null;

        B binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), getLayoutId(), parent, false);

        try {
            vh = constructor.newInstance(binding.getRoot());
            constructor.setAccessible(false);
            vh.setBinding(binding);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return vh;

    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(VH holder, int position) {


        B binding = holder.getBinding();
        M item = getItem(position);
        try {
            Method method = binding.getClass().getMethod("set" + item.getClass().getSimpleName(), item.getClass());
            method.invoke(binding, item);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
//        TestModel model = getItem(position);
//        //because TestModel extends BaseObservable　，so we can do this directly
//        binding.setTestModel(model);


        onControlView( item ,binding,holder, position);
    }

    public abstract void onControlView(M item , B binding, VH holder,  int position );


    public
    @LayoutRes
    abstract int getLayoutId();



}
