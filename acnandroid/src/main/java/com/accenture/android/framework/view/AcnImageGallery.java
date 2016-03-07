package com.accenture.android.framework.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.accenture.android.framework.R;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by ugurcan.yildirim on 07.03.2016.
 */
public class AcnImageGallery extends LinearLayout {

    private Context context;
    private int heightInPx;

    public AcnImageGallery(Context context) {
        super(context);
        this.init(context, null);
    }

    public AcnImageGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context, attrs);
    }

    public AcnImageGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = inflate(context, R.layout.acn_imagegallery, this);

        this.setOrientation(VERTICAL);

        this.context = context;

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int widthInPx = Math.min(display.getHeight(), display.getWidth()); //ACCORDING TO MINIMUM WIDTH-ED SIDE
        heightInPx = widthInPx / 3;

    }

    public void setImagesFromURLs(final ArrayList<String> imageURLs){

        this.removeAllViews();

        for(int i = 0; i < imageURLs.size(); i = i + 2) {
            View child = inflate(context, R.layout.item_imagepair, null);
            this.addView(child, LinearLayout.LayoutParams.MATCH_PARENT, heightInPx);

            //LEFTSIDE IMAGE
            AcnImageView image_1 = (AcnImageView) child.findViewById(R.id.image_1);

            String imageURL = imageURLs.get(i);
            image_1.setImageFromURL(imageURL, false);

            //RIGHTSIDE IMAGE
            AcnImageView image_2 = (AcnImageView) child.findViewById(R.id.image_2);
            if(i + 1 == imageURLs.size()) { //NO RIGHTSIDE IMAGE
                image_2.setVisibility(View.GONE);
                break;
            }
            else {
                imageURL = imageURLs.get(i + 1);
                image_2.setImageFromURL(imageURL, false);
            }

        }

    }

}
