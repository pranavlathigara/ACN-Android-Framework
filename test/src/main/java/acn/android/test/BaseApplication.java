package acn.android.test;

import acn.android.framework.context.AcnApplication;

/**
 * Created by ugurcan.yildirim on 02.03.2016.
 */
public class BaseApplication extends AcnApplication {

    public BaseApplication(){
        super("Gotham-Black.ttf", "LOGGER", 300);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

}
