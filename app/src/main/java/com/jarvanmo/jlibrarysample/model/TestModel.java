package com.jarvanmo.jlibrarysample.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.jarvanmo.jlibrarysample.BR;


/**
 * Created by mo on 16-3-28.
 *
 */
public class TestModel extends BaseObservable {

    @Bindable
    private String userName;
    @Bindable
    private String sex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.sex);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        notifyPropertyChanged(BR.sex);
        this.sex = sex;
    }
}
