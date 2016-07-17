package com.kun.imageloader.utils.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by kun on 16/7/16.
 */
public class MemoryCache implements ImageCache {
    LruCache<String, Bitmap> mImageCache;

    public MemoryCache() {
        initCache();
    }


    @Override
    public void initCache() {
        final int memorySize = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = memorySize / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bmp) {
        mImageCache.put(url, bmp);
    }

    @Override
    public Bitmap get(String url) {
        return mImageCache.get(url);
    }
}
