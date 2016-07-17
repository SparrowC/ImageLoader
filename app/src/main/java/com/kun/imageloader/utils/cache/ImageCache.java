package com.kun.imageloader.utils.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by kun on 2016/7/12.
 */
public interface ImageCache {


    void initCache();

    void put(String url, Bitmap bmp);

    Bitmap get(String url);


}
