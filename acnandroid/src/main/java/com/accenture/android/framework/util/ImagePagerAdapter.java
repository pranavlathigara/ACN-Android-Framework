package com.accenture.android.framework.util;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.accenture.android.framework.view.AcnImageView;

import java.util.ArrayList;

/**
 * Created by ugurcan.yildirim on 08.03.2016.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private Context context;
    private int fourDpInPixels;

    private ArrayList<String> imageURLs;
    private boolean zoomable;

    public ImagePagerAdapter(Context context, ArrayList<String> imageURLs, boolean zoomable) {

        this.context = context;
        this.fourDpInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, context.getResources().getDisplayMetrics());

        this.imageURLs = imageURLs;
        this.zoomable = zoomable;

    }

    @Override
    public int getCount() {
        return imageURLs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((AcnImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        final String imageURL = imageURLs.get(position);

        AcnImageView acnImageView = new AcnImageView(context);
        acnImageView.setImageFromURL(imageURL, zoomable);
        acnImageView.setPadding(fourDpInPixels, fourDpInPixels, fourDpInPixels, fourDpInPixels);

        container.addView(acnImageView);

        return acnImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        AcnImageView acnImageView = (AcnImageView) object;
        try {
            if (acnImageView.getAttacher() != null)
                acnImageView.getAttacher().cleanup();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        container.removeView(acnImageView);
    }

}
