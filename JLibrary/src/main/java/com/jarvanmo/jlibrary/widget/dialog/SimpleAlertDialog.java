package com.jarvanmo.jlibrary.widget.dialog;

import android.support.v4.app.FragmentActivity;

/**
 * Created by mo on 16-3-26.
 *
 */
public class SimpleAlertDialog {


    public static SimpleDialogBuilder with(FragmentActivity activity){
        return  new SimpleDialogBuilder(activity);

    }


    private SimpleAlertDialog(){
        throw new AssertionError("No instances.");
    }
}
