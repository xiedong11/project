package com.example.project.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import com.example.project.SelectActivity;

/**
 * Created by xiedong on 2017/4/14.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("---- yo get the message  ---"+intent.getStringExtra("value"));
        Toast.makeText(context,"yo",Toast.LENGTH_LONG).show();

    }
}
