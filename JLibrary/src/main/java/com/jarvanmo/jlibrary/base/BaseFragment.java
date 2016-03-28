package com.jarvanmo.jlibrary.base;

import android.support.annotation.StringRes;

import com.jarvanmo.jlibrary.widget.toast.JToast;

/**
 * Created by mo on 16-3-26.
 *
 */
public class BaseFragment {

    protected void showToast(String message){
        JToast.showToast(message);
    }

    protected void showToast(@StringRes int messageId){
        JToast.showToast(messageId);
    }

}
