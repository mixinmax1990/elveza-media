package com.news.elvezanews.Interfaces;

import org.json.JSONArray;
import org.json.JSONObject;

public interface VolleyRequestResponse {
    public void onSuccessJson(JSONArray result, String type);
    public void onFailureJson(int responseCode, String responseMessage);
}
