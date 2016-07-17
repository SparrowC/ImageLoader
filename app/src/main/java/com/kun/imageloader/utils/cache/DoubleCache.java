package com.kun.imageloader.utils.cache;

import android.graphics.Bitmap;

/**
 * Created by kun on 16/7/17.
 */
public class DoubleCache implements ImageCache {
    private MemoryCache memoryCache = new MemoryCache();
    private DiskCache diskCache = new DiskCache();

    @Override
    public void initCache() {

    }

    @Override
    public void put(String url, Bitmap bmp) {
        diskCache.put(url,bmp);
        memoryCache.put(url,bmp);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bmp=memoryCache.get(url);
        if(bmp==null)
            bmp=diskCache.get(url);
        return bmp;
    }
}
