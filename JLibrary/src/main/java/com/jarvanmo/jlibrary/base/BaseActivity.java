package com.jarvanmo.jlibrary.base;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.jarvanmo.jlibrary.system.SystemManager;
import com.jarvanmo.jlibrary.system.SystemSetting;
import com.jarvanmo.jlibrary.widget.toast.JToast;

/**
 * Created by mo on 16-3-26.
 *
 */
public class BaseActivity extends AppCompatActivity {
    public SystemManager manager = SystemManager.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager.addActivity(this);
    }

    protected void showToast(String message){
        JToast.showToast(message);
    }

    protected void showToast(@StringRes int messageId){
        JToast.showToast(messageId);
    }


    protected void startActivityOrService(Class<?> clazz){
        startActivityOrService(clazz,null);
    }

    protected void startActivityOrService(Class<?> clazz,Bundle bundle){
        Intent intent = new Intent(this,clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }

        if(BaseActivity.class.isAssignableFrom(clazz)){
            startActivity(intent);
        }else if(Service.class.isAssignableFrom(clazz)){
            startService(intent);
        }
    }




    @Override
    public void finish() {
        super.finish();
        manager.removeActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            manager.finishCurrActivity(this);
        }
        return false;
    }

    @Override
    public Resources getResources() {
        if(!SystemSetting.ResourceConfig.isAppFontSizeControlledByOs) {
            return super.getResources();
        }else {

            Resources resources = super.getResources();
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            resources.updateConfiguration(configuration,resources.getDisplayMetrics());

            return  resources;
        }
    }
}
