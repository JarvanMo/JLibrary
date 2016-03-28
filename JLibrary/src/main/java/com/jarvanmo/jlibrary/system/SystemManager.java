package com.jarvanmo.jlibrary.system;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.jarvanmo.jlibrary.widget.toast.JToast;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;


public class SystemManager {
    private boolean isExit = false;

    private List<Activity> mList = new LinkedList<>();
    // 为了实现每次使用该类时不创建新的对象而创建的静态对象
    private static SystemManager instance;


    // 构造方法
    private SystemManager() {

    }

    // 实例化一次
    public synchronized static SystemManager getInstance() {
        if (null == instance) {
            instance = new SystemManager();
        }
        return instance;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    // 关闭每一个list内的activity
    public void exit() {

        for(Runnable r : SystemSetting.SystemExitConfig.doTheseBeforeExit){
            r.run();
        }

        try {
            for (int i = mList.size() - 1; i >= 0; i--) {
                Activity activity = mList.get(i);
                if (activity != null) {
                    activity.finishAffinity();
                    activity.finish();
                    mList.remove(activity);

                }
            }

            JToast.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 某些activity不需要记录
    public void removeActivity(Activity activity) {
        if (mList != null) {
            mList.remove(activity);
        }
    }

    public Activity getCurrActivity() {
        return mList.get(mList.size() - 1);
    }


    public void finishThisActivity(Activity activity) {
        for (int i = mList.size() - 1; i >= 0; i--) {
            Activity a = mList.get(i);


            if (null != a && activity.getClass().toString().equals(a.getClass().toString())) {
                mList.remove(a);

                a.finish();
            }
        }
    }


    public void finishCurrActivity(Activity activity) {

        for (int i = mList.size() - 1; i >= 0; i--) {
            Activity a = mList.get(i);


            String name = activity.getClass().getSimpleName();
            //quitHere永远不为空


            if (a != null && a.getClass().getSimpleName().equals(name)) {

                if (SystemSetting.SystemExitConfig.exitNames.contains(name)) {
                    doExit(activity);
                    return;
                } else {
                    mList.remove(a);
                    a.finish();
                    return;
                }
            }
        }
    }


    private Handler handler = new ManagerHandler(this);


    private void doExit(Activity activity) {

        if (!isExit) {
            isExit = true;
            JToast.showToast(SystemSetting.SystemExitConfig.exitNotice);
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            activity.finish();
            activity.finishAffinity();
            exit();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                }
            },500);

        }

    }



    private static class ManagerHandler extends  Handler{

        private WeakReference<SystemManager> weakReference ;
        public ManagerHandler(SystemManager systemManager){
            weakReference = new WeakReference<>(systemManager);
        }

        @Override
        public void handleMessage(Message msg) {
          weakReference.get().isExit = false;
        }
    }
}
