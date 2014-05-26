package com.naosim.contentvolley.app;

import android.content.Context;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by fujitanao on 2014/05/26.
 */
public class ContentVolley {
    public static RequestQueue newRequestQueue(Context context) {
        Network network = new ContentNetwork(context.getContentResolver());
        RequestQueue queue = new RequestQueue(new EmptyDiskCache(), network);
        queue.start();
        return queue;
    }

    public static ImageLoader newImageLoader(Context context) {
        RequestQueue queue = newRequestQueue(context.getApplicationContext());
        return new ImageLoader(queue, new LruImageCache());
    }
}
