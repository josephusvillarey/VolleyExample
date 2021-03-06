package com.example.volleyexample;

import com.android.volley.Response;

import java.util.Map;

/**
 * Created by josephus on 29/10/2016.
 */

public class GetUserRequest extends GsonRequest<User> {
    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url           URL of the request to make
     * @param clazz         Relevant class object, for Gson's reflection
     * @param headers       Map of request headers
     * @param listener
     * @param errorListener
     */
    public GetUserRequest(String url, Class<User> clazz, Map<String, String> headers, Response.Listener<User> listener, Response.ErrorListener errorListener) {
        super(url, clazz, headers, listener, errorListener);
    }
}