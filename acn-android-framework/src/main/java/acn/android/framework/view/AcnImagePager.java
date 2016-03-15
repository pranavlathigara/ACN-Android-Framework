package acn.android.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.thefinestartist.utils.content.ResourcesUtil;
import com.thefinestartist.utils.content.TypedValueUtil;

import java.util.ArrayList;

import acn.android.framework.R;
import acn.android.framework.view.adapter.ImagePagerAdapter;
import acn.android.framework.view.helper.HackyViewPager;

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

            try {
                //INDICATOR COLOR
                selectedIndicatorColor = a.getColor(R.styleable.AcnImagePager_selectedIndicatorColor, ResourcesUtil.getColor(android.R.color.black));
                unselectedIndicatorColor = a.getColor(R.styleable.AcnImagePager_unselectedIndicatorColor, ResourcesUtil.getColor(android.R.color.darker_gray));

                //INDICATOR SIZE
                selectedIndicatorSize = (int) a.getDimension(R.styleable.AcnImagePager_selectedIndicatorSize, TypedValueUtil.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8));
                unselectedIndicatorSize = (int) a.getDimension(R.styleable.AcnImagePager_unselectedIndicatorSize, TypedValueUtil.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6));

                //INDICATOR BOTTOM MARGIN
                int bottomMargin = (int) a.getDimension(R.styleable.AcnImagePager_indicatorBottomMargin, TypedValueUtil.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20));
                pagerIndicator.setPadding(0, 0, 0, bottomMargin);
            } finally {
                a.recycle();
            }
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
