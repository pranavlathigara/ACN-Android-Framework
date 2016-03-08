package com.accenture.android.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.accenture.android.framework.R;
import com.mingle.widget.LoadingView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by ugurcan.yildirim on 02.03.2016.
 */
public class AcnImageView extends FrameLayout {

    enum ImageType {
        URL, DRAWABLE, ASSET
    }

    private ImageView imageView;
    private LoadingView loadingView;
    private GifImageView gifImageView;

    private PhotoViewAttacher attacher;

    private boolean customGifEnabled = false;

    public AcnImageView(Context context) {
        super(context);
        this.init(context, null);
    }

    public AcnImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context, attrs);
    }

    public AcnImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        View view = inflate(context, R.layout.acn_imageview, this);

        imageView = (ImageView) view.findViewById(R.id.imageView);
        gifImageView = (GifImageView) view.findViewById(R.id.gifImageView);
        loadingView = (LoadingView) view.findViewById(R.id.loadingView);

        if(attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AcnImageView, 0, 0);

            try {
                //SCALE TYPE
                int scaleType = a.getInteger(R.styleable.AcnImageView_scaleType, 1);
                if(scaleType == 0){
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }
                else if(scaleType == 1){
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                else if(scaleType == 2){
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }

                //GIF SOURCE
                String gifSrc = a.getString(R.styleable.AcnImageView_gifSrc);
                if(gifSrc != null){
                    customGifEnabled = true;
                    try {
                        //GIF SIZE
                        float eightyDpInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
                        int gifSize = (int) a.getDimension(R.styleable.AcnImageView_gifSize, eightyDpInPixels);
                        gifImageView.getLayoutParams().width = gifSize;

                        GifDrawable gifFromAssets = new GifDrawable(context.getAssets(), gifSrc);
                        gifImageView.setImageDrawable(gifFromAssets);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }

                //DOT COLOR
                /*int dotColor = a.getColor(R.styleable.AcnImageView_dotColor, getResources().getColor(android.R.color.darker_gray));
                loadingView.setDotColor(dotColor);

                //DOT COUNT
                int dotCount = a.getInteger(R.styleable.AcnImageView_dotCount, 3);
                loadingView.setNumberOfDots(dotCount);

                //DOT RADIUS
                float fiveDpInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
                float dotRadius = a.getDimension(R.styleable.AcnImageView_dotRadius, fiveDpInPixels);
                loadingView.setDotRadius(dotRadius);*/

            } finally {
                a.recycle();
            }
        }

    }

    public void setImageFromURL(String imageURL, boolean zoomable){
        this.setImage(imageURL, zoomable, ImageType.URL);
    }

    public void setImageFromDrawable(int imageDrawable, boolean zoomable){
        this.setImage(imageDrawable, zoomable, ImageType.DRAWABLE);
    }

    public void setImageFromAssets(String imageAsset, boolean zoomable){
        this.setImage(imageAsset, zoomable, ImageType.ASSET);
    }

    private <T> void setImage(T imageRes, boolean zoomable, ImageType imageType){

        String image = "";
        if(imageType == ImageType.URL)
            image = (String) imageRes;
        else if(imageType == ImageType.DRAWABLE)
            image = "drawable://" + imageRes;
        else if(imageType == ImageType.ASSET)
            image = "assets://" + imageRes;

        if(zoomable && attacher == null) {
            attacher = new PhotoViewAttacher(imageView);
        }

        ImageLoader.getInstance().displayImage(image, imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if(customGifEnabled)
                    gifImageView.setVisibility(View.VISIBLE);
                else
                    loadingView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if(customGifEnabled)
                    gifImageView.setVisibility(View.GONE);
                else
                    loadingView.setVisibility(View.GONE);

                if(attacher != null)
                    attacher.update();
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });

    }

}
