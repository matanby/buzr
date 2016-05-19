package com.example.iraltman.buzr;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oranja on 19/05/16.
 */
public class Category {
    public int id;
    public String name;

    // Mock
    public Category() {
        this.id = 2;
        this.name = "Shoes";
    }

    // Real
    public Category(JSONObject data_json) {
        try {
            this.id = data_json.getInt("id");
            this.name = data_json.getString("name");
        } catch (JSONException je) {
        }
    }
}
