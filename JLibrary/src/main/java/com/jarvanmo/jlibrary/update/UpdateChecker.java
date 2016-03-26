package com.jarvanmo.jlibrary.update;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;

import com.jarvanmo.jlibrary.R;
import com.jarvanmo.jlibrary.widget.dialog.DialogFragmentListener;
import com.jarvanmo.jlibrary.widget.dialog.SimpleAlertDialog;


public class UpdateChecker extends Fragment  implements DialogFragmentListener {

    //private static final String NOTIFICATION_ICON_RES_ID_KEY = "resId";
    private static final String NOTICE_TYPE_KEY = "type";
    private static final String APP_UPDATE_SERVER_URL = "";

    public static final String APK_UPDATE_SERVER = "apk_update_server";
    public static final String APK_UPDATE_CONTENT = "apk_update_content";
    public static final String APK_DOWNLOAD_URL = "apk_download_url";
    public static final String APK_VERSION_CODE = "version";
    
    //private static final String SUCCESSFUL_CHECKS_REQUIRED_KEY = "nChecks";
    private static final int NOTICE_NOTIFICATION = 2;
    private static final int NOTICE_DIALOG = 1;
    private static final String TAG = "UpdateChecker";

    //    private static FragmentActivity mContext;
    private Context mContext;
    private Thread mThread;
    private int mTypeOfNotice;
    private String msg;
    private String url;

    /**
     * Show a Dialog if an update is available for download. Callable in a
     * FragmentActivity. Number of checks after the dialog will be shown:
     * default, 5
     */

//    public UpdateChecker(FragmentActivity context) {
//        mContext = context;
//    }
    public UpdateChecker() {

    }

    public static void checkForDialog(FragmentActivity fragmentActivity, String url) {
        FragmentTransaction content = fragmentActivity.getSupportFragmentManager().beginTransaction();

        UpdateChecker updateChecker = new UpdateChecker();
        Bundle args = new Bundle();
//		int versionCode = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
        args.putInt(NOTICE_TYPE_KEY, NOTICE_DIALOG);
        args.putString(APP_UPDATE_SERVER_URL, url);
        //args.putInt(SUCCESSFUL_CHECKS_REQUIRED_KEY, 5);
        updateChecker.setArguments(args);
        content.add(updateChecker, null).commitAllowingStateLoss();
    }


    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
//        SimpleDialogFragment.with(getActivity()).attach(this).message(R.string.update_or_not).showDialog();
        SimpleAlertDialog.with(getActivity()).message(R.string.update_or_not).title(R.string.new_version_available).listen(this).show();
    }

    /**
     * @param fragmentActivity must extends {@link FragmentActivity}
     * @param msg              app update messages
     * @param url              apk download url
     **/
    public static void notifyUpdate(final FragmentActivity fragmentActivity, final String msg, final String url) {

//        Dialog dialog = new AlertDialog.Builder(fragmentActivity)//
//                .setTitle(R.string.update_or_not)//
//                .setMessage(msg)//
//                .setPositiveButton(R.string.btn_positive, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        UpdateChecker.checkForNotification(fragmentActivity, msg, url);
//
//                    }
//                })
//                .setNegativeButton(R.string.btn_negative, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })//
//                .create();
//
//
//        dialog.show();

    }

    /**
     * Show a Notification if an update is available for download. Callable in a
     * FragmentActivity Specify the number of checks after the notification will
     * be shown.
     *
     * @param fragmentActivity Required.
     */
    public static void checkForNotification(FragmentActivity fragmentActivity, String msg, String url) {
//        mContext =  fragmentActivity;


        FragmentTransaction content = fragmentActivity.getSupportFragmentManager().beginTransaction();
        UpdateChecker updateChecker = new UpdateChecker();
        Bundle args = new Bundle();
        args.putInt(NOTICE_TYPE_KEY, NOTICE_NOTIFICATION);
        args.putString(APP_UPDATE_SERVER_URL, url);
        args.putString("msg", msg);
//        args.putString("APP_UPDATE_MSG", msg);
        //args.putInt(NOTIFICATION_ICON_RES_ID_KEY, notificationIconResId);
        //args.putInt(SUCCESSFUL_CHECKS_REQUIRED_KEY, 5);
        updateChecker.setArguments(args);
        content.add(updateChecker, null).commitAllowingStateLoss();

    }


    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = getArguments();
        msg = bundle.getString("msg");
        url = bundle.getString(APP_UPDATE_SERVER_URL);
//        showNotification(getActivity(),msg,url);


    }

    /**
     * Show dialog
     */
    public void showDialog(String content, String apkUrl) {
        UpdateDialog d = new UpdateDialog();
        Bundle args = new Bundle();
        args.putString(UpdateChecker.APK_UPDATE_CONTENT, content);
        args.putString(UpdateChecker.APK_DOWNLOAD_URL, apkUrl);
        d.setArguments(args);
        d.show(((FragmentActivity) mContext).getSupportFragmentManager(), null);
    }


    /**
     * Show Notification
     */
    public  void showNotification(FragmentActivity context, String content, String apkUrl) {
        android.app.Notification noti;
        Intent myIntent = new Intent(context, DownloadService.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        myIntent.putExtra(UpdateChecker.APK_DOWNLOAD_URL, apkUrl);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        int smallIcon = context.getApplicationInfo().icon;
        noti = new NotificationCompat.Builder(context).setTicker(context.getString(R.string.new_version_available)).setDefaults(NotificationCompat.DEFAULT_SOUND)
                .setContentTitle(context.getString(R.string.new_version_available)).setContentText(content).setSmallIcon(smallIcon)
                .setContentIntent(pendingIntent).build();

        noti.flags = NotificationCompat.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, noti);
    }

    /**
     * Show Notification
     */
    public void showNotification(String content, String apkUrl) {
        android.app.Notification noti;
        Intent myIntent = new Intent(mContext, DownloadService.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        myIntent.putExtra(UpdateChecker.APK_DOWNLOAD_URL, apkUrl);
        PendingIntent pendingIntent = PendingIntent.getService(mContext, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        int smallIcon = mContext.getApplicationInfo().icon;
        noti = new NotificationCompat.Builder(mContext).setTicker(getString(R.string.new_version_available))
                .setContentTitle(getString(R.string.new_version_available)).setContentText(content).setSmallIcon(smallIcon)
                .setContentIntent(pendingIntent).build();

        noti.flags = android.app.Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, noti);
    }


    /**
     * Check if a network available
     */
    public static boolean isNetworkAvailable(Context context) {
        boolean connected = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni != null) {
                connected = ni.isConnected();
            }
        }
        return connected;
    }


    @Override
    public void onPositive() {
        showNotification(getActivity(), msg, url);
    }

    @Override
    public void onNegative() {

    }
}
