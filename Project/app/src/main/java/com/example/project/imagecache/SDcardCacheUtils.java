package com.example.project.imagecache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *    本地缓存
 * Created by xiedong on 2017/3/16.
 */

public class SDcardCacheUtils {

    private static final String CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"zhuandian";

    public Bitmap getFromSDcard(String url){
        String fileName = url.substring(0,5);
        File file = new File(CACHE_PATH,fileName);

        if(file.exists()){
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));

                System.out.println("------------- load Iamge from SDcard----------");
                return bitmap;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }





    public void saveToSDcard(String url ,Bitmap bitmap){
        String fileName = url.substring(0,5);

        File file = new File(CACHE_PATH,fileName);


        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
