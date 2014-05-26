package com.naosim.contentvolley.app;

import com.android.volley.Cache;

/**
 * Created by fujitanao on 2014/05/26.
 */
public class EmptyDiskCache implements Cache {
    @Override
    public Entry get(String key) {
        return null;
    }

    @Override
    public void put(String key, Entry entry) {

    }

    @Override
    public void initialize() {

    }

    @Override
    public void invalidate(String key, boolean fullExpire) {

    }

    @Override
    public void remove(String key) {

    }

    @Override
    public void clear() {

    }
}
