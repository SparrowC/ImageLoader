package com.kun.imageloader.utils.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;
import com.kun.imageloader.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by kun on 16/7/16.
 */
public class DiskCache implements ImageCache {

    final static String cacheDir = MainActivity.cachePath;

//    static String cacheDir = null;

    public DiskCache() {
        initCache();
    }


    @Override
    public void initCache() {
    }


//    @Override
//    public void put(String url, Bitmap bmp) {
//        File dir = new File(cacheDir);
//        if (!dir.exists())
//            dir.mkdirs();
//        File file = new File(cacheDir + "/" + parseStrToMd5L32(url) + ".jpg");
//        FileOutputStream fos = null;
//        try {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            fos = new FileOutputStream(file);
//            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {//关闭输出流
//            if (fos != null)
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }
//    }

    @Override
    public void put(String url, Bitmap bmp) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("/sdcard/cache/" + parseStrToMd5L32(url));
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {//关闭输出流
            if (fos != null)
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    @Override
    public Bitmap get(String url) {
        //return BitmapFactory.decodeFile(cacheDir + "/" + parseStrToMd5L32(url) + ".jpg");
        return BitmapFactory.decodeFile("/sdcard/cache/" + parseStrToMd5L32(url));
    }

    public String getFileName(String str) {
        return str.hashCode() + "";
    }

    public String parseStrToMd5L32(String str) {
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes) {
                int bt = b & 0xff;
                if (bt < 16) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            reStr = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reStr;
    }
}
