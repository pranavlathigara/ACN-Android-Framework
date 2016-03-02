package com.accenture.android.framework.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.accenture.android.framework.util.Config;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by ugurcan.yildirim on 02.03.2016.
 */
public class AcnButton extends FancyButton {

    public AcnButton(Context context) {
        super(context);
        this.setDefFont();
    }

    public AcnButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setDefFont();
    }

    private void setDefFont(){

        if(!TextUtils.isEmpty(Config.defaultFont)){
            this.setCustomTextFont(Config.defaultFont);
        }

    }

}
