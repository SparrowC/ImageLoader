package com.kun.imageloader.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Vonnie on 2016/7/12.
 */
public class ImageCache {
    LruCache<String, Bitmap> mImageCache;

    public ImageCache() {
        initImageCache();
    }

    private void initImageCache() {
        final int memorySize = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = memorySize / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public void put(String url ,Bitmap bmp){

    }

    public Bitmap get(String url) {
        return null;
    }
}
