package com.jarvanmo.jlibrary.base;

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
import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter<E, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    private List<E> data;


    private Context mContext;

    private int layoutResId;

    private Class<VH> clazz;

    private RecycleViewAdapter(Context context, List<E> data, @LayoutRes int layoutResId, Class<VH> clazz) {
        this.mContext = context;
        this.layoutResId = layoutResId;
        if (data == null) {
            data = new ArrayList<>();
        }

        this.data = data;
        this.clazz = clazz;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    public E getItem(int position) {
        if (position < data.size()) {
            return data.get(position);
        } else {
            return null;
        }
    }

    public Context getContext() {
        return mContext;
    }

    public List<E> getData() {
        return data;
    }


    /**
     * @return return the last one of  data
     * if the size of data is 0 or data is null
     * it will return null
     **/
    public E getLastItem() {

        E result = null;
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
    public void setData(List<E> data) {
        if (data == null) {
            data = new ArrayList<>();
        }

        this.data = data;
        notifyDataSetChanged();
    }

    /**
     * add a piece of data to the adapter
     *
     * @param item a piece of data
     */
    public void addItem(E item) {

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
    public void addAll(List<E> moreData) {

        if (data != null) {
            data.addAll(moreData);
            notifyDataSetChanged();
        }
    }

    /**
     * add a item to the data at index
     **/
    public void add(int position, E item) {

        if (data != null) {
            data.add(position, item);
            notifyDataSetChanged();
        }
    }

    public void add(E item) {
        if (data != null) {
            data.add(item);
            notifyDataSetChanged();
        }
    }


    /**
     * add a item to the data at the first position
     **/
    public void addToFirst(E item) {
        add(0, item);
    }

    public void set(int position, E item) {

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

    public void removeItem(E item) {

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

//        View view = inflater.inflate(layoutResId, parent, false);

        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), layoutResId, parent, false);
        try {
            Constructor<VH> constructor;
            constructor = clazz.getDeclaredConstructor(View.class);
            constructor.setAccessible(true);
            vh = constructor.newInstance(binding.getRoot());
            constructor.setAccessible(false);
            vh.setBinding(binding);
//            RecycleViewViewHolderBuilder.with(context).load(view).into(vh);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return vh;

    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(VH holder, int position) {


        ViewDataBinding binding = holder.getBinding();
        E item = getItem(position);
        try {
            Method method = binding.getClass().getMethod("set" + item.getClass().getSimpleName(), item.getClass());
            method.invoke(binding, item);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
//        TestModel model = getItem(position);
//        //because TestModel extends BaseObservable　，so we can do this directly
//        binding.setTestModel(model);


        onControlView(holder, position);
    }

    public void onControlView(VH holder, int position) {

    }


    public static class Builder<M> {

        private Context context;
        private List<M> data;
        private int layoutId;
        private Class clazz;

        public Builder(Context context) {
            this.context = context;
        }


        public Builder setData(List<M> data) {
            this.data = data;
            return this;
        }

        public Builder setLayout(@LayoutRes int layoutId) {

            this.layoutId = layoutId;

            return this;
        }


        public Builder setHolder(Class<?> clazz){
            this.clazz = clazz;
            return this;
        }

        public RecycleViewAdapter create() {

            final RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(context,data,layoutId,clazz);


            return recycleViewAdapter;

        }

    }

}
