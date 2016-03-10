package com.accenture.android.test.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.accenture.android.framework.view.AcnTextView;
import com.accenture.android.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ugurcan.yildirim on 09.03.2016.
 */
public class ViewPagerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager, container, false);
        ButterKnife.bind(this, rootView);

        Bundle bundle = getArguments();
        String text = bundle.getString("text");

        acn_textview.setText(text);

        return rootView;
    }

    @Bind(R.id.acn_textview)
    AcnTextView acn_textview;

}
