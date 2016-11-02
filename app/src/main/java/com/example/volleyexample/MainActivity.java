package com.example.volleyexample;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    ImageView imageView;
    NetworkImageView networkImageView;
    private EditText usernameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image);
        networkImageView = (NetworkImageView) findViewById(R.id.networkImageView);

        usernameET = (EditText) findViewById(R.id.username_et);

        networkImageView.setImageUrl("https://avatars.githubusercontent.com/u/583231?v=3", ((VolleyExampleApplication) getApplicationContext()).getmImageLoader());
    }

    public void fetchImage(View view) {

        String url = "https://avatars.githubusercontent.com/u/583231?v=3";

        ImageRequest imageRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        ((VolleyExampleApplication) getApplicationContext()).addToRequestQueue(imageRequest, TAG);
    }

    public void sendSampleRequest(View view) {

        usernameET = (EditText) findViewById(R.id.username_et);

        String username = usernameET.getText().toString();

        // GET /users/:username/repos

        String url = "https://api.github.com/users/" + username + "/repos";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG, "response: " + response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        ((VolleyExampleApplication) getApplicationContext()).addToRequestQueue(stringRequest, TAG);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ((VolleyExampleApplication) getApplicationContext()).cancelRequest(TAG);
    }

    public void getUsersGson(View view) {

        String username = usernameET.getText().toString();

        // GET /users/:username

        String url = "https://api.github.com/users/" + username;

        Map<String, String> headers = new HashMap<>();

        GetUserRequest getUserRequest = new GetUserRequest(url, User.class, headers, new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {

                Log.d(TAG, response.getHtml_url());

                Toast.makeText(MainActivity.this, response.getHtml_url(), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error: " + error.getMessage());
            }
        });

        ((VolleyExampleApplication) getApplicationContext()).addToRequestQueue(getUserRequest, TAG);
    }

    public void listReposOfUser(View view) {
        usernameET = (EditText) findViewById(R.id.username_et);

        String username = usernameET.getText().toString();

        // GET /users/:username/repos

        String url = "https://api.github.com/users/" + username + "/repos";

        Map<String, String> headers = new HashMap<>();

        GetReposRequest request = new GetReposRequest(url, Repo[].class, headers, new Response.Listener<Repo[]>() {
            @Override
            public void onResponse(Repo[] response) {
                Log.d(TAG, "response count: " + response.length);
                for (Repo repo : response) {
                    Log.d(TAG, "repo url: " + repo.getUrl());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        ((VolleyExampleApplication) getApplicationContext()).addToRequestQueue(request, TAG);

    }
}
