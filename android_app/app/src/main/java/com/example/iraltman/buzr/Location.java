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
            this.mapURL = "http://132.65.251.197:8080" + data_json.getString("map_photo_url");
        } catch (JSONException je) {
        }
    }
}

