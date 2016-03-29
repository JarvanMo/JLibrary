package com.jarvanmo.jlibrary.base.context;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.jarvanmo.jlibrary.system.SystemManager;
import com.jarvanmo.jlibrary.system.SystemSetting;
import com.jarvanmo.jlibrary.widget.toast.JToast;

import okhttp3.OkHttpClient;

/**
 * Created by mo on 16-3-26.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initSystem();
        initFresco();
    }

    private void initSystem() {
        JToast.init(this);
        SystemManager.getInstance();
        SystemSetting.SystemExitConfig.exitNames.add(SystemSetting.SystemExitConfig.defaultExitName);
    }


    private void initFresco() {
        Fresco.initialize(this);


    }

}
