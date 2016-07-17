package com.kun.imageloader.utils.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by kun on 16/7/16.
 */
public class DiskCache implements ImageCache {

    static String cacheDir = "sdcard/cache/";

    public DiskCache() {
        initCache();
    }


    @Override
    public void initCache() {

    }

    @Override
    public void put(String url, Bitmap bmp) {
        FileOutputStream fos = null;
        try {
            fos=new FileOutputStream(cacheDir+url);
            bmp.compress(Bitmap.CompressFormat.PNG,100,fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {//关闭输出流
            if(fos!=null)
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir + url);
    }
}
