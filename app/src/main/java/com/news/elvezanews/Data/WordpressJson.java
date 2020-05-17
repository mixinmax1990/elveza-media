package com.news.elvezanews.Data;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.news.elvezanews.Fragments.NewsFragment;
import com.news.elvezanews.Fragments.NumbersFragment;
import com.news.elvezanews.Interfaces.VolleyRequestResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.LogRecord;

public class WordpressJson {

    private String getURL;

    private RequestQueue mQueueNumbers;
    private Context context;
    VolleyRequestResponse responseListener;
    private String typeOfRequest = "test";

    public WordpressJson(VolleyRequestResponse responseListener, String getURL, Context context, String type) {
        this.getURL = getURL;
        this.context = context;
        this.typeOfRequest = type;
        this.responseListener = responseListener;

        mQueueNumbers = Volley.newRequestQueue(context);
        jsonParse(getURL);
    }


    private void jsonParse(String getURL){

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, getURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Get The Data
                responseListener.onSuccessJson(response, typeOfRequest);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueueNumbers.add(request);


    }

}
