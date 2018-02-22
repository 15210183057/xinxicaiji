package com.example.a123456.zhonggu;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import application.MyApplication;
import base.BaseActivity;
import bean.UserBean;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        findViewById(R.id.HelloTV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });
        //停留1秒自动跳转
        Thread myThread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Log.e("TAG","y用户名=="+UserBean.username);
                    if(!TextUtils.isEmpty(UserBean.username)&&!TextUtils.isEmpty(UserBean.password)){
                        Intent intent=new Intent(MainActivity.this,FrameActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        myThread.start();
    }
}
