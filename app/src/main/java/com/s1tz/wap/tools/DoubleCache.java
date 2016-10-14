package com.s1tz.wap.tools;

import android.graphics.Bitmap;

/**
 * author:jiangyou
 * date:16-10-11
 * desc:双缓存，内存和手机上都缓存，读取的时候先读内存里边的,之后读手机上的
 */

public class DoubleCache implements ImageCache {
    ImageCache mMemoryCache = new MemoryCache();
    ImageCache mDiskCache = new DiskCache();

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null) {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }
}
