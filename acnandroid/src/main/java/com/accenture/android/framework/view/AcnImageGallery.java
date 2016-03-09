package com.accenture.android.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.accenture.android.framework.R;
import com.accenture.android.framework.util.ImageClickHandler;

import java.util.ArrayList;

/**
 * Created by ugurcan.yildirim on 07.03.2016.
 */
public class AcnImageGallery extends LinearLayout {

    private Context context;
    private int heightInPx;
    private int spacing;
    private int columnCount = 2;

    private ImageClickHandler imageClickHandler;

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

        if(attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AcnImageGallery, 0, 0);

            try {
                float fourDpInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
                spacing = (int) a.getDimension(R.styleable.AcnImageGallery_spacing, fourDpInPixels);

                columnCount = a.getInteger(R.styleable.AcnImageGallery_columnType, 2);

            } finally {
                a.recycle();
            }
        }

        this.setOrientation(VERTICAL);

        this.context = context;

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int widthInPx = Math.min(display.getHeight(), display.getWidth()); //ACCORDING TO MINIMUM WIDTH-ED SIDE
        heightInPx = widthInPx / 3;

    }

    public void setImageClickHandler(ImageClickHandler imageClickHandler){
        this.imageClickHandler = imageClickHandler;
    }

    public void setImagesFromURLList(final ArrayList<String> imageURLs){

        if(columnCount == 2)
            this.setImagesInPairs(imageURLs);
        else if(columnCount == 3)
            this.setImagesInTriplets(imageURLs);

    }

    private void setImagesInPairs(final ArrayList<String> imageURLs){

        this.removeAllViews();

        for(int i = 0; i < imageURLs.size(); i = i + 2) {
            View child = inflate(context, R.layout.item_imagepair, null);
            this.addView(child, LinearLayout.LayoutParams.MATCH_PARENT, heightInPx);

            AcnImageView image_1 = (AcnImageView) child.findViewById(R.id.image_1);
            AcnImageView image_2 = (AcnImageView) child.findViewById(R.id.image_2);

            //SPACING
            child.setPadding(0, 0, 0, spacing);

            View gap = child.findViewById(R.id.gap);
            gap.getLayoutParams().width = spacing;

            //LEFTSIDE IMAGE
            int position1 = i;
            String url1 = imageURLs.get(i);

            initImage(image_1, position1, url1);

            //RIGHTSIDE IMAGE
            int position2 = i + 1;
            if(position2 == imageURLs.size()) { //NO RIGHTSIDE IMAGE
                image_2.setVisibility(View.GONE);
                break;
            }
            else {
                String url2 = imageURLs.get(i + 1);
                initImage(image_2, position2, url2);
            }

        }

    }

    private void setImagesInTriplets(final ArrayList<String> imageURLs){

        this.removeAllViews();

        for(int i = 0; i < imageURLs.size(); i = i + 3) {
            View child = inflate(context, R.layout.item_imagetriplet, null);
            this.addView(child, LinearLayout.LayoutParams.MATCH_PARENT, heightInPx);

            AcnImageView image_1 = (AcnImageView) child.findViewById(R.id.image_1);
            AcnImageView image_2 = (AcnImageView) child.findViewById(R.id.image_2);
            AcnImageView image_3 = (AcnImageView) child.findViewById(R.id.image_3);

            //SPACING
            child.setPadding(0, 0, 0, spacing);

            View gap_1 = child.findViewById(R.id.gap_1);
            gap_1.getLayoutParams().width = spacing;

            View gap_2 = child.findViewById(R.id.gap_2);
            gap_2.getLayoutParams().width = spacing;

            //LEFTSIDE IMAGE
            int position1 = i;
            String url1 = imageURLs.get(i);

            initImage(image_1, position1, url1);

            //CENTER IMAGE
            int position2 = i + 1;
            if(position2 == imageURLs.size()) { //NO CENTER IMAGE
                image_2.setVisibility(View.GONE);
                image_3.setVisibility(View.GONE);
                break;
            }
            else {
                String url2 = imageURLs.get(i + 1);
                initImage(image_2, position2, url2);

                //RIGHTSIDE IMAGE
                int position3 = i + 2;
                if(position3 == imageURLs.size()) { //NO RIGHTSIDE IMAGE
                    image_3.setVisibility(View.GONE);
                    break;
                }
                else {
                    String url3 = imageURLs.get(i + 2);
                    initImage(image_3, position3, url3);
                }

            }

        }

    }

    private void initImage(AcnImageView acnImageView, final int position, final String imageURL){

        acnImageView.setImageFromURL(imageURL, false);
        acnImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageClickHandler.onImageClicked(position, imageURL);
            }
        });

    }

}
