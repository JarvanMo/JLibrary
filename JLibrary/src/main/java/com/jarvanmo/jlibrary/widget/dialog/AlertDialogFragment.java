package com.jarvanmo.jlibrary.widget.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.Window;

import com.jarvanmo.jlibrary.R;

/**
 * Created by mo on 16-3-26.
 */
public class AlertDialogFragment extends DialogFragment {

    public static final String KEY_MESSAGE = "message";
    public static final String KEY_TITLE = "title";
    public static final String KEY_POSITIVE_TEXT = "positiveText";
    public static final String KEY_NEGATIVE_TEXT = "negativeText";

    private DialogFragmentListener listener;


//    private String msg = "";
//    private String positiveText ="";
//    private String negativeText = "";
//    private String title = "";

    @Override
    public void onStart() {
        super.onStart();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bundle bundle = getArguments();
        String msg = bundle.getString(KEY_MESSAGE, "");
        String positiveText = bundle.getString(KEY_POSITIVE_TEXT, "");
        String negativeText = bundle.getString(KEY_NEGATIVE_TEXT, "");
        String title = bundle.getString(KEY_TITLE, "");

        if ("".equals(positiveText)) {
            positiveText = getString(R.string.default_positive_text);
        }

        if ("".equals(negativeText)) {
            negativeText = getString(R.string.default_negative_text);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg);
        builder.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onPositive();
                }
            }
        });

        if (!"".equals(title)) {
            builder.setTitle(title);
        }

        builder.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    dismiss();
                    listener.onNegative();
                }
            }
        });

        return builder.create();
    }

    public void setListener(DialogFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listener = null;
    }
}
