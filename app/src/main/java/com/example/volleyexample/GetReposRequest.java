package com.example.volleyexample;

import com.android.volley.Response;

import java.util.Map;

/**
 * Created by josephus on 29/10/2016.
 */

public class GetReposRequest extends GsonRequest<Repo[]> {
    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url           URL of the request to make
     * @param clazz         Relevant class object, for Gson's reflection
     * @param headers       Map of request headers
     * @param listener
     * @param errorListener
     */
    public GetReposRequest(String url, Class<Repo[]> clazz, Map<String, String> headers, Response.Listener<Repo[]> listener, Response.ErrorListener errorListener) {
        super(url, clazz, headers, listener, errorListener);
    }
}
