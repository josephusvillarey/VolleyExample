package com.example.volleyexample;

import android.app.Application;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by josephus on 29/10/2016.
 */

public class VolleyExampleApplication extends Application {
    private static final String TAG = VolleyExampleApplication.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private ImageLoader mImageLoader;

    public ImageLoader getmImageLoader() {
        return mImageLoader;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

        mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(
                LruBitmapCache.getCacheSize(this)));

// Start the queue
        mRequestQueue.start();
    }

    public void addToRequestQueue(Request request, Object tag) {
        request.setTag(tag);
        mRequestQueue.add(request);
    }

    public void cancelRequest(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}
