package com.example.iraltman.buzr;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oranja on 19/05/16.
 */
public class Store {
    public int id;
    public String name;
    public String address;
    public String locationId;
    public int categoryId;

    // Mock
    public Store() {
        this.id = 100;
        this.name = "Castro Shoes";
        this.address = "Fifth Floor";
        this.locationId = "";
        this.categoryId = 2;
    }

    // Real
    public Store(JSONObject data_json) {
        try {
            this.id = data_json.getInt("id");
            this.name = data_json.getString("name");
            this.address = data_json.getString("address");
            this.locationId = data_json.getString("location_id");
            this.categoryId = data_json.getInt("category_id");
        } catch (JSONException je) {
        }
    }
}
