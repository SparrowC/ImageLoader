package com.kun.imageloader.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.kun.imageloader.utils.cache.ImageCache;
import com.kun.imageloader.utils.cache.MemoryCache;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kun on 2016/7/12.
 * 使用时 需要调用 setCache方法来设置缓存类型(自定义缓存 内存缓存 本地缓存 双缓存)
 */
public class ImageLoader {
    private ImageCache imageCache = new MemoryCache();

    ExecutorService mExecutorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    /*
     *在使用时先设置缓存类型
     */
    public void setCache(ImageCache cache) {
        this.imageCache = cache;
    }

    public void displayImage(String url, ImageView imageView) {
        Bitmap bmp = imageCache.get(url);
        //判断是否有缓存
        if (bmp == null) {
            submitLoadRequest(url,imageView);
        } else {
            imageView.setImageBitmap(bmp);
        }

    }

    private void submitLoadRequest(final String url, final ImageView imageView) {
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bmp = downloadImage(url);
                if (bmp == null)
                    return;
                if (imageView.getTag().equals(url))
                    imageView.setImageBitmap(bmp);
                imageCache.put(url, bmp);
            }
        });
    }

    private Bitmap downloadImage(String imageUrl) {
        Bitmap bmp = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            bmp = BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

}
