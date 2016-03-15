package acn.android.framework.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import acn.android.framework.util.Config;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by ugurcan.yildirim on 02.03.2016.
 */
public class AcnButton extends FancyButton {

    public AcnButton(Context context) {
        super(context);
        this.setFont();
    }

    public AcnButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setFont();
    }

    private void setFont(){

        if(!TextUtils.isEmpty(Config.appFont)){
            this.setCustomTextFont(Config.appFont);
        }

    }

}
