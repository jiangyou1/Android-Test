package com.s1tz.wap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.s1tz.wap.tools.ImageLoader;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_first = (ImageView) findViewById(R.id.iv_first);

        ImageLoader imageLoader = new ImageLoader();
        imageLoader.displayImage("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1476028109&di=17bce5ac1a02f9a97a787cadc1285e98&src=http://photocdn.sohu.com/20111231/Img330861134.jpg", iv_first);
    }
}
