package com.s1tz.wap.tools;

import java.io.Serializable;

/**
 * author:jiangyou
 * date:16-10-18
 * desc:
 */

public class ImageLoaderConfig {
    ImageCache bitmapCache = new MemoryCache();
    int mLoadingImage;
    int mLoadingFailedImage;
    int threadCount = Runtime.getRuntime().availableProcessors();

    private ImageLoaderConfig() {
    }

    public static class Builder {
        ImageCache bitmapCache = new MemoryCache();
        int mLoadingImage;
        int mLoadingFailedImage;
        int threadCount = Runtime.getRuntime().availableProcessors();

        public Builder setThreadCount(int count) {
            threadCount = Math.max(1, count);
            return this;
        }

        public Builder setCache(ImageCache cache) {
            bitmapCache = cache;
            return this;
        }

        public Builder setLoadingImageholder(int resId) {
            mLoadingImage = resId;
            return this;
        }

        public Builder setLoadingFailerImageholder(int resId) {
            mLoadingFailedImage = resId;
            return this;
        }

        void applyConfig(ImageLoaderConfig config) {
            config.bitmapCache = this.bitmapCache;
            config.mLoadingImage = this.mLoadingImage;
            config.mLoadingFailedImage = this.mLoadingFailedImage;
            config.threadCount = this.threadCount;
        }

        public ImageLoaderConfig create() {
            ImageLoaderConfig config = new ImageLoaderConfig();
            applyConfig(config);
            return config;
        }
    }
}
