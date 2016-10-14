package com.s1tz.wap.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author:jiangyou
 * date:16-10-11
 * desc:
 */

public class ImageLoader {

    private ImageCache mImageCache = new MemoryCache();
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    public void setImageCache(ImageCache _imageCahce) {
        this.mImageCache = _imageCahce;
    }

    public void displayImage(String _url, ImageView _imageView) {
        Bitmap bitmap = mImageCache.get(_url);
        if (bitmap != null) {
            _imageView.setImageBitmap(bitmap);
            return;
        }
        submitLoadRequest(_url, _imageView);
    }

    private void submitLoadRequest(final String _url, final ImageView _imageView) {
        _imageView.setTag(_url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(_url);

                if (bitmap == null) {
                    return;
                }

                if (_imageView.getTag().equals(_url)) {
                    _imageView.setImageBitmap(bitmap);
                }

                mImageCache.put(_url, bitmap);
            }
        });
    }

    public Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            Log.e("ImageLoader", e.toString());
        }
        return bitmap;
    }

}
