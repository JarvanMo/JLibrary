package com.jarvanmo.jlibrarysample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jarvanmo.jlibrary.util.JLog;
import com.jarvanmo.jlibrary.widget.dialog.DialogFragmentListener;
import com.jarvanmo.jlibrary.widget.dialog.SimpleAlertDialog;
import com.jarvanmo.jlibrary.widget.toast.JToast;

public class MainActivity extends AppCompatActivity implements DialogFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sampleForJLog();
        Button jtoast_btn_one = (Button) findViewById(R.id.jtoast_one_btn);
        assert jtoast_btn_one != null;
        jtoast_btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JToast.showToast(R.string.jtoast_one);

                JLog.w("warn");
            }
        });

        Button jtoast_btn_two = (Button) findViewById(R.id.jtoast_two_btn);
        assert jtoast_btn_two != null;
        jtoast_btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JToast.showToast("I love Rock -->" + System.currentTimeMillis());

                JLog.w("warn");
            }
        });

        findViewById(R.id.simple_dialog_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleAlertDialog.with(MainActivity.this).title(R.string.update_title) //
                        .message("test").positiveText(R.string.default_positive_text).//
                        negativeText(R.string.default_negative_text).listen(MainActivity.this).show();
            }
        });
    }

    @Override
    public void onPositive() {
        Toast.makeText(this,"done positive",Toast.LENGTH_SHORT).show();
        JLog.i("info");
    }

    @Override
    public void onNegative() {
        Toast.makeText(this,"done negative",Toast.LENGTH_SHORT).show();
        JLog.e("error","negative");
    }

    private void sampleForJLog(){
        JLog.i("info","info");
        JLog.d("debug","debug");
    }
}
