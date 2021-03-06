package acn.android.framework.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

import com.thefinestartist.utils.content.ResourcesUtil;
import com.thefinestartist.utils.content.TypedValueUtil;

import java.util.ArrayList;

import acn.android.framework.R;
import acn.android.framework.view.adapter.ViewPagerAdapter;
import acn.android.framework.view.helper.SlidingTabLayout;

/**
 * Created by ugurcan.yildirim on 08.03.2016.
 */
public class AcnViewPager extends RelativeLayout {

    private Context context;

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    private ViewPagerAdapter viewPagerAdapter;

    private float tabTitleSize;
    private int indicatorColor;
    private int dividerColor;
    private ColorStateList textColorStateList;

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

        if(attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AcnViewPager, 0, 0);

            try {
                //TAB TITLE SIZE
                tabTitleSize = a.getDimension(R.styleable.AcnViewPager_tabTitleSize, TypedValueUtil.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14));

                //TAB BACKGROUND COLOR
                int tabBackgroundColor = a.getColor(R.styleable.AcnViewPager_tabBackgroundColor, ResourcesUtil.getColor(android.R.color.white));
                slidingTabLayout.setBackgroundColor(tabBackgroundColor);

                //INDICATOR COLOR
                indicatorColor = a.getColor(R.styleable.AcnViewPager_indicatorColor,ResourcesUtil.getColor(android.R.color.black));

                //DIVIDER COLOR
                dividerColor = a.getColor(R.styleable.AcnViewPager_dividerColor, ResourcesUtil.getColor(android.R.color.darker_gray));

                //TEXT COLOR
                int selectedTitleColor = a.getColor(R.styleable.AcnViewPager_selectedTitleColor, ResourcesUtil.getColor(android.R.color.black));
                int unselectedTitleColor = a.getColor(R.styleable.AcnViewPager_unselectedTitleColor, ResourcesUtil.getColor(android.R.color.darker_gray));

                int[][] states = new int[][] {
                        new int[] {android.R.attr.state_selected},
                        new int[] {-android.R.attr.state_selected}
                };
                int[] colors = new int[] {
                        selectedTitleColor,
                        unselectedTitleColor
                };
                textColorStateList = new ColorStateList(states, colors);

            } finally {
                a.recycle();
            }

        }

    }

    public void setContent(final ArrayList<Fragment> fragments, final ArrayList<String> tabTitles){

        viewPagerAdapter = new ViewPagerAdapter((AppCompatActivity)context, fragments, tabTitles);
        viewPager.setAdapter(viewPagerAdapter);

        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return indicatorColor;
            }
            @Override
            public int getDividerColor(int position) {
                return dividerColor;
            }
        });
        slidingTabLayout.setViewPager(viewPager, tabTitleSize, textColorStateList);

    }

}
