package com.example.project.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xiedong on 2017/4/20.
 */

public class Utils {
    public static void toast(Context context , String s){
        Toast.makeText(context,s ,Toast.LENGTH_LONG).show();
    }
}
