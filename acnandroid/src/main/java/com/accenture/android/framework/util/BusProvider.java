package com.accenture.android.framework.util;

import com.squareup.otto.Bus;

/**
 * Created by ugurcan.yildirim on 02.03.2016.
 */
/**
 * Maintains a singleton instance for obtaining the bus. Ideally this would be replaced with a more efficient means
 * such as through injection directly into interested classes.
 */
public final class BusProvider {

    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }

}