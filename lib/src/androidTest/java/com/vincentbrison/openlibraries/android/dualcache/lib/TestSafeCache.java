package com.vincentbrison.openlibraries.android.dualcache.lib;

import android.test.AndroidTestCase;

import com.vincentbrison.openlibraries.android.dualcache.lib.testobjects.AbstractVehicle;
import com.vincentbrison.openlibraries.android.dualcache.lib.testobjects.CoolCar;

import java.util.concurrent.Callable;

/**
 * Created by vnicolaou on 08/05/16.
 */
public class TestSafeCache extends AndroidTestCase {

    protected static final int RAM_MAX_SIZE = 1000;
    protected static final int DISK_MAX_SIZE = 20 * RAM_MAX_SIZE;
    protected static final String CACHE_NAME = "test";
    protected static final int TEST_APP_VERSION = 0;
    SafeCache<AbstractVehicle> vehicleSafeCache;

    public void setUp() throws Exception {
        super.setUp();
        vehicleSafeCache = new DualCacheBuilder<AbstractVehicle>(CACHE_NAME, TEST_APP_VERSION, AbstractVehicle.class)
                .useReferenceInRam(RAM_MAX_SIZE, new DualCacheTest.SizeOfVehiculeForTesting())
                .noDisk().safeCache();
    }

    int called = 0;

    public void test_get_set() {
        for (int i = 0; i < 2; i++) {
            AbstractVehicle abstractVehicle = vehicleSafeCache.get("test_vehicle", new Callable<AbstractVehicle>() {
                @Override
                public AbstractVehicle call() throws Exception {
                    CoolCar coolCar = new CoolCar();
                    called++;
                    return coolCar;
                }
            });
            assertEquals(CoolCar.class, abstractVehicle.getClass());
        }
        assertEquals(1, called);
    }
}
