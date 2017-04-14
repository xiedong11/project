package com.example.project.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.project.Receiver.MyReceiver;

/**
 * Created by xiedong on 2017/4/14.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
       // super.onCreate();

        System.out.println("service is onCreate---------");
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        System.out.println("service is onStartCommand---------");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 11160; i++) {

//                    for (int j = 0; j < 600; j++)
                    {
                        System.out.println(i+"-----------dddddd----------");
                    }
                }

//                Intent intent1 = new Intent("com.zhuandian.MYRECEIVER");
                Intent intent1 = new Intent(MyService.this,MyReceiver.class);
                intent1.putExtra("value","yo this is message");

                sendBroadcast(intent1);
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
      //  super.onDestroy();
        System.out.println("service is onDestroy---------");
    }


}
