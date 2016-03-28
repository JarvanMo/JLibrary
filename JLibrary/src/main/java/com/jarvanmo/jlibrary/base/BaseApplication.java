package com.jarvanmo.jlibrary.base;

import android.app.Application;

import com.jarvanmo.jlibrary.system.SystemManager;
import com.jarvanmo.jlibrary.system.SystemSetting;
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
        SystemManager.getInstance();
        SystemSetting.SystemExitConfig.exitNames.add(SystemSetting.SystemExitConfig.defaultExitName);
    }
}
