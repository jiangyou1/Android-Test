package com.s1tz.wap.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.LruCache;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jiangyou on 16-10-9.
 */

public class ImageLoader {

    LruCache<String,Bitmap> mImageCache;
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    public ImageLoader(){
        initImageCache();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    private void initImageCache(){
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);
        final int cacheSize = maxMemory/4;
        mImageCache = new LruCache<String,Bitmap>(cacheSize){

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
    }

    public void displayImage(final String url,final ImageView imageView){
        imageView.setTag(url);
        mExecutorService.submit(new Runnable(){

            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if(bitmap ==null){
                    return;
                }
                if(imageView.getTag().equals(url)){
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(url,bitmap);
            }
        });
    }

    public Bitmap downloadImage(String imageUrl){
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

}
