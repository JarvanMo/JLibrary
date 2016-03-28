package com.jarvanmo.jlibrary.widget.toast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by mo on 16-3-28.
 * JToast is a util for toast
 */
public final class JToast {

    private static Toast mToast = null;
    private static Context mContext = null;

    private static long oneTime = 0;
    private static long twoTime = 0;

    private static String oldMessage = "";

    /***
     * Must call this method before use JToast
     * usually, we init JToast in our application
     **/
    public static void init(Context context) {
        mContext = context;
    }


    /***
     * Usually , you'd better  call　 {@link #showToast(int)}　instead,
     * because we need internationalization sometimes.
     **/
    public static void showToast(@NonNull String message) {

        if (mToast == null) {
            mToast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
            mToast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (oldMessage.equals(message)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    mToast.show();
                }
            } else {
                oldMessage = message;
                mToast.setText(oldMessage);
                mToast.show();
            }
        }

        oneTime = twoTime;
    }

    public static void showToast(@StringRes int messageId) {
        showToast(mContext.getString(messageId));
    }


    /**
     * Close the view if it's showing, or don't show it if it isn't showing yet.
     * You do not normally have to call this.  Normally view will disappear on its own
     * after the appropriate duration.
     */
    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }

    }

}
