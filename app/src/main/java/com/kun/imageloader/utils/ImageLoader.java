package com.kun.imageloader.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Vonnie on 2016/7/12.
 */
public class ImageLoader {
    private ImageCache mImageCache = new ImageCache();

    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bmp = mImageCache.get(url);
        if (bmp == null)
        {
            bmp=downloadImage(url);
        }
    }

    private Bitmap downloadImage(String url) {
        return null;
    }
}
