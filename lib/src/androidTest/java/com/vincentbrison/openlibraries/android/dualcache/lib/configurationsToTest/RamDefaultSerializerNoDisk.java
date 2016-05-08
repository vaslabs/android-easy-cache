package com.vincentbrison.openlibraries.android.dualcache.lib.configurationsToTest;

import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheBuilder;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheTest;
import com.vincentbrison.openlibraries.android.dualcache.lib.testobjects.AbstractVehicle;

/**
 * Created by Brize on 04/10/2014.
 */
public class RamDefaultSerializerNoDisk extends DualCacheTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mCache = new DualCacheBuilder<AbstractVehicle>(CACHE_NAME, TEST_APP_VERSION, AbstractVehicle.class)
                .useDefaultSerializerInRam(RAM_MAX_SIZE)
                .noDisk();
    }
}
