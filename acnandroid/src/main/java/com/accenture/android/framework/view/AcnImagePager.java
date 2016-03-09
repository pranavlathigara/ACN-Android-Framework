package com.accenture.android.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

import com.accenture.android.framework.R;
import com.accenture.android.framework.util.ImagePagerAdapter;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import java.util.ArrayList;

/**
 * Created by ugurcan.yildirim on 08.03.2016.
 */
public class AcnImagePager extends RelativeLayout {

    private Context context;

    private SliderLayout sliderLayout;
    private HackyViewPager hackyViewPager;
    private PagerIndicator pagerIndicator;

    private ImagePagerAdapter imagePagerAdapter;

    private int selectedIndicatorColor;
    private int unselectedIndicatorColor;

    private int selectedIndicatorSize;
    private int unselectedIndicatorSize;

    public AcnImagePager(Context context) {
        super(context);
        this.init(context, null);
    }

    public AcnImagePager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context, attrs);
    }

    public AcnImagePager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = inflate(context, R.layout.acn_imagepager, this);

        this.context = context;

        sliderLayout = (SliderLayout) view.findViewById(R.id.sliderLayout);
        hackyViewPager = (HackyViewPager) view.findViewById(R.id.hackyViewPager);
        pagerIndicator = (PagerIndicator) view.findViewById(R.id.pagerIndicator);

        if(attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AcnImagePager, 0, 0);

            //INDICATOR COLOR
            selectedIndicatorColor = a.getColor(R.styleable.AcnImagePager_selectedIndicatorColor, getResources().getColor(android.R.color.black));
            unselectedIndicatorColor = a.getColor(R.styleable.AcnImagePager_unselectedIndicatorColor, getResources().getColor(android.R.color.darker_gray));

            //INDICATOR SIZE
            float eightDpInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
            float sixDpInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics());
            selectedIndicatorSize = (int) a.getDimension(R.styleable.AcnImagePager_selectedIndicatorSize, eightDpInPixels);
            unselectedIndicatorSize = (int) a.getDimension(R.styleable.AcnImagePager_unselectedIndicatorSize, sixDpInPixels);

            //INDICATOR BOTTOM MARGIN
            float twentyDpInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
            int bottomMargin = (int) a.getDimension(R.styleable.AcnImagePager_indicatorBottomMargin, twentyDpInPixels);
            pagerIndicator.setPadding(0, 0, 0, bottomMargin);
        }

    }

    public void setImagesFromURLList(final ArrayList<String> imageURLs, boolean zoomable){

        imagePagerAdapter = new ImagePagerAdapter(context, imageURLs, zoomable);
        hackyViewPager.setOffscreenPageLimit(1);
        hackyViewPager.setAdapter(imagePagerAdapter);
        hackyViewPager.setCurrentItem(0);
        hackyViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                sliderLayout.setCurrentPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        pagerIndicator.setDefaultIndicatorColor(selectedIndicatorColor, unselectedIndicatorColor);
        pagerIndicator.setDefaultSelectedIndicatorSize(selectedIndicatorSize, selectedIndicatorSize, PagerIndicator.Unit.Px);
        pagerIndicator.setDefaultUnselectedIndicatorSize(unselectedIndicatorSize, unselectedIndicatorSize, PagerIndicator.Unit.Px);

        sliderLayout.removeAllSliders();
        for(int i=0; i<imageURLs.size(); i++){
            DefaultSliderView defaultSliderView = new DefaultSliderView(context);
            sliderLayout.addSlider(defaultSliderView);
        }
        sliderLayout.stopAutoCycle();
        sliderLayout.setCustomIndicator(pagerIndicator);
        sliderLayout.setCurrentPosition(0);

    }

}
