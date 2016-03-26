package com.jarvanmo.jlibrary.widget.dialog;

import android.gesture.Prediction;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;

import com.jarvanmo.jlibrary.util.Preconditions;

/**
 * Created by mo on 16-3-26.
 *
 */
public class SimpleDialogBuilder {

    private FragmentActivity fragmentActivity;
    private String message = "";
    private String positiveText ="";
    private String negativeText = "";
    private String title = "";
    private DialogFragmentListener listener;



    public SimpleDialogBuilder(FragmentActivity activity){
        this.fragmentActivity = activity;
    }

    public SimpleDialogBuilder message(String message) {

       this.message = message;
        return this;
    }


    public SimpleDialogBuilder message(int msgId) {

        message(Preconditions.checkNotNull(fragmentActivity).getString(msgId));
        return this;
    }


    private SimpleDialogBuilder positiveText(String positiveText){
        this.positiveText = positiveText;
        return this;
    }

    public SimpleDialogBuilder positiveText(@StringRes int positiveTextId){
        return positiveText(Preconditions.checkNotNull(fragmentActivity).getString(positiveTextId));
    }

    private SimpleDialogBuilder negativeText(String negativeText){
        this.negativeText = negativeText;
        return this;
    }

    public SimpleDialogBuilder negativeText(@StringRes int negativeTextId){
        return negativeText(Preconditions.checkNotNull(fragmentActivity).getString(negativeTextId));
    }



    public SimpleDialogBuilder title(String title){
        this.title = title;
        return this;
    }

    public SimpleDialogBuilder title(@StringRes int titleId){
        return title(Preconditions.checkNotNull(fragmentActivity).getString(titleId));
    }


    public SimpleDialogBuilder listen(DialogFragmentListener listener){
        this.listener = listener;
        return this;
    }


    public AlertDialogFragment show(){

        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(AlertDialogFragment.KEY_MESSAGE,message);
        bundle.putString(AlertDialogFragment.KEY_TITLE,title);
        bundle.putString(AlertDialogFragment.KEY_POSITIVE_TEXT,positiveText);
        bundle.putString(AlertDialogFragment.KEY_NEGATIVE_TEXT,negativeText);
        alertDialogFragment.setArguments(bundle);
        alertDialogFragment.setListener(listener);
        alertDialogFragment.show(fragmentActivity.getSupportFragmentManager(),message);
        return  alertDialogFragment;
    }
}
