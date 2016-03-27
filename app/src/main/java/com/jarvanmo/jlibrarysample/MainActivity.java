package com.jarvanmo.jlibrarysample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jarvanmo.jlibrary.util.JLog;
import com.jarvanmo.jlibrary.widget.dialog.DialogFragmentListener;
import com.jarvanmo.jlibrary.widget.dialog.SimpleAlertDialog;

public class MainActivity extends AppCompatActivity implements DialogFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        JLog.d("---");
        Log.w("aa","[1: MainActivity.java:37]\n" +
                "hello world\n");
        Log.println(Log.ERROR,"cc","c");
        Log.i("dd","dd");

        findViewById(R.id.hello_world_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleAlertDialog.with(MainActivity.this).title(R.string.update_title) //
                        .message("test").positiveText(R.string.default_positive_text).//
                        negativeText(R.string.default_negative_text).listen(MainActivity.this).show();
                JLog.w("hi");
            }
        });
    }

    @Override
    public void onPositive() {
        Toast.makeText(this,"done positive",Toast.LENGTH_SHORT).show();

        JLog.i("hello world");
    }

    @Override
    public void onNegative() {
        Toast.makeText(this,"done negative",Toast.LENGTH_SHORT).show();
        JLog.w("nothing","negative");
    }
}
