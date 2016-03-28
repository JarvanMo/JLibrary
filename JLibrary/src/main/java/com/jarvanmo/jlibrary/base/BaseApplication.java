package com.jarvanmo.jlibrary.base;

import android.app.Application;

import com.jarvanmo.jlibrary.widget.toast.JToast;

/**
 * Created by mo on 16-3-26.
 *
 */
public class BaseApplication  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JToast.init(this);
    }
}
