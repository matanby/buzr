package com.example.iraltman.buzr.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.example.iraltman.buzr.rest.AndrestClient;

import org.json.JSONObject;

import java.util.Map;

public class RestRequest extends AsyncTask<Void, JSONObject, JSONObject> {
    private AndrestClient rest = new AndrestClient();
    private Exception e;
    private String url;

    public RestRequest(String url) {
        this.url = url;
    }

    @Override
    protected JSONObject doInBackground(Void... arg0) {
        try {
            return rest.request(url, "GET", null); // Do request
        } catch (Exception e) {
            this.e = e; // Store error
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject data){
        super.onPostExecute(data);
        if(e != null){
            Log.e(getClass().getSimpleName(), e.getMessage());
        } else {
            Log.i(getClass().getSimpleName(), "Success: " + this.url);
        }
    }
}