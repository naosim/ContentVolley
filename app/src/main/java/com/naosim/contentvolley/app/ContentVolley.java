package com.naosim.contentvolley.app;

import android.content.Context;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader;

import java.io.File;

/**
 * Created by fujitanao on 2014/05/26.
 */
public class ContentVolley {
    /**
     * Default on-disk cache directory.
     */
    private static final String DEFAULT_CACHE_DIR = "volley";

    public static RequestQueue newRequestQueue(Context context) {
        File cacheDir = new File(context.getCacheDir(), DEFAULT_CACHE_DIR);
        Network network = new ContentNetwork(context.getContentResolver());
        RequestQueue queue = new RequestQueue(new DiskBasedCache(cacheDir), network);
        queue.start();
        return queue;
    }

    public static ImageLoader newImageLoader(Context context) {
        RequestQueue queue = newRequestQueue(context.getApplicationContext());
        return new ImageLoader(queue, new LruImageCache());
    }
}
