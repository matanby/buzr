package com.example.iraltman.buzr;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by oranja on 19/05/16.
 */
public class Deal {
    public int id;
    public int storeId;
    public String description;
    public String photoUrl;
    public Date startTime;
    public Date endTime;

    // Mock
    public Deal() {
        this.id = 4;
        this.storeId = 5;
        this.description = "Not a big deal.";
        this.photoUrl = "http://crackberry.com/sites/crackberry.com/files/styles/large/public/topic_images/2013/ANDROID.png?itok=xhm7jaxS";

        this.startTime = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.startTime);
        calendar.add(Calendar.HOUR, 1);
        this.endTime = calendar.getTime();
    }

    // Real
    public Deal(JSONObject data_json) {
        try {
            this.id = data_json.getInt("id");
            this.storeId = data_json.getInt("store_id");
            this.description = data_json.getString("description");
            this.photoUrl = data_json.getString("photo_url");
            this.startTime = new Date(data_json.getLong("start_time"));
            this.endTime = new Date(data_json.getLong("end_time"));
        } catch (JSONException je) {
        }
    }
}
