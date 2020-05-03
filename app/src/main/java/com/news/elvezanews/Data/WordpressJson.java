package com.news.elvezanews.Data;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.news.elvezanews.Fragments.NewsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.LogRecord;

public class WordpressJson {

    private String getURL;
    private RequestQueue mQueue;
    private Context context;
    NewsFragment parent;

    public WordpressJson(String getURL, Context context, NewsFragment parent) {
        this.getURL = getURL;
        this.context = context;
        this.parent = parent;

        mQueue = Volley.newRequestQueue(context);

        jsonParse(getURL);

    }

    private static JSONArray data;
    private void jsonParse(String getURL){

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, getURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Get The Data

                Log.i("REsponse", ""+response);

                data = response;

                final Handler handler = new Handler();
                final Runnable r = new Runnable() {
                    public void run() {
                        parent.loadNewsRecyclerAdapter(data);
                    }
                };

                handler.postDelayed(r, 1000);


                for (int i = 0; i < response.length(); i++){

                    try {
                        JSONObject post = response.getJSONObject(i);

                        Log.i("Get Json Data", ""+post.getString("title")+" -- "+post.getString("id"));


                        // Run a method in NewsFragment
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
        mQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<JsonArrayRequest>() {

            @Override
            public void onRequestFinished(Request<JsonArrayRequest> request) {

                Log.i("Request Finished", "works");


            }
        });

    }

}
