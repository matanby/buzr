package com.example.iraltman.buzr;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oranja on 19/05/16.
 */
public class Category {
    public int id;
    public String name;
    public String iconUrl;

    // Mock
    public Category() {
        this.id = 2;
        this.name = "Shoes";
        this.iconUrl = "http://webneel.com/sites/default/files/images/manual/logo-restaurant/best-restaurant-logo-design%20(2).gif";
    }

    // Real
    public Category(JSONObject data_json) {
        try {
            this.id = data_json.getInt("id");
            this.name = data_json.getString("name");
            this.iconUrl = data_json.getString("icon_url");
        } catch (JSONException je) {
        }
    }
}
