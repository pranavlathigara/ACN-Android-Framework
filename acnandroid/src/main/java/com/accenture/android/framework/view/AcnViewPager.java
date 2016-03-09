package com.accenture.android.framework.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.accenture.android.framework.R;
import com.accenture.android.framework.adapter.ViewPagerAdapter;
import com.accenture.android.framework.helper.HackyViewPager;
import com.accenture.android.framework.helper.SlidingTabLayout;

import java.util.ArrayList;

/**
 * Created by ugurcan.yildirim on 08.03.2016.
 */
public class AcnViewPager extends RelativeLayout {

    private Context context;

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    private ViewPagerAdapter viewPagerAdapter;

    public AcnViewPager(Context context) {
        super(context);
        this.init(context, null);
    }

    public AcnViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context, attrs);
    }

    public AcnViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = inflate(context, R.layout.acn_viewpager, this);

        this.context = context;

        slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.slidingTabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        /*if(attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AcnImagePager, 0, 0);


        }*/

    }

    public void setContent(final ArrayList<Fragment> fragments, final ArrayList<String> tabTitles){

        viewPagerAdapter = new ViewPagerAdapter((AppCompatActivity)context, fragments, tabTitles);
        viewPager.setAdapter(viewPagerAdapter);

        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(android.R.color.black);
            }
            @Override
            public int getDividerColor(int position) {
                return getResources().getColor(android.R.color.darker_gray);
            }
        });
        slidingTabLayout.setViewPager(viewPager);

    }

}
