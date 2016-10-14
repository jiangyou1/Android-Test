package com.s1tz.wap.tools;


import android.graphics.Bitmap;

/**
 * author:jiangyou
 * date:16-10-11
 * desc:缓存的接口
 */

public interface ImageCache {

    public Bitmap get(String url);

    public void put(String url, Bitmap bitmap);


}
