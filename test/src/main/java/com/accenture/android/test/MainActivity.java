package com.accenture.android.test;

import android.os.Bundle;
import android.widget.ImageView;

import com.accenture.android.framework.context.AcnActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;

public class MainActivity extends AcnActivity {

    public MainActivity(){
        super(R.layout.activity_main, R.id.toolbar, R.color.StatusBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageLoader.getInstance().displayImage("http://goo.gl/Kd34Xq", imageView);

    }

    @Bind(R.id.imageView)
    ImageView imageView;

}
