package com.example.iraltman.buzr;

import android.os.AsyncTask;
import org.json.JSONObject;

import java.util.Map;

class RestRequest extends AsyncTask<Void, JSONObject, JSONObject> {
    private AndrestClient rest = new AndrestClient();
    private Exception e;
    private String url;
    private Map<String, Object> data = null;

    public RestRequest(String url, Map<String, Object> data) {
        this.url = url;
        this.data = data;
    }

    @Override
    protected JSONObject doInBackground(Void... arg0) {
        try {
            return rest.request(url, "GET", data); // Do request
        } catch (Exception e) {
            this.e = e; // Store error
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject data){
        super.onPostExecute(data);
        // Display based on error existence
        if(e != null){
//            new ResponseDialog(context, "We found an error!", e.getMessage()).showDialog();
        } else {
//            new ResponseDialog(context, "Success!", data.toString()).showDialog();
        }
    }
}