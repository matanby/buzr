package com.example.iraltman.buzr;

import org.json.JSONException;
import org.json.JSONObject;

public class Location {

    public String id;
    public String description;
    public String mapURL;

    // Real
    public Location(JSONObject data_json) {
        try {
            this.id = data_json.getString("id");
            this.description = data_json.getString("description");
            this.mapURL = R.string.endpoint + data_json.getString("mapURL");
        } catch (JSONException je) {
        }
    }
}

