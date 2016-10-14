package com.s1tz.wap.tools;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * author:jiangyou
 * date:16-10-11
 * desc:缓存到手机内存里边
 */

public class MemoryCache implements ImageCache {

    //图片LRU缓存
    LruCache<String, Bitmap> mImageCache;

    public MemoryCache() {
        initImageCache();
    }

    @Override
    public Bitmap get(String url) {
        return mImageCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
    }

    private void initImageCache() {
        //计算可使用的最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //取四分之一的可用内存作为缓存
        final int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }
}
