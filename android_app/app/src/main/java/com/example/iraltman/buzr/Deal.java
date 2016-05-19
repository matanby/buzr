package com.example.iraltman.buzr;


import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by oranja on 19/05/16.
 */
public class Deal {
    public final int id;
    public final int storeId;
    public final String description;
    public final Date startTime;
    public final Date endTime;

    public Deal(JSONObject json) {
        this.id = 5;
        this.storeId = 5;
        this.description = "Not a big deal.";

        this.startTime = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.startTime);
        calendar.add(Calendar.HOUR, 1);
        this.endTime = calendar.getTime();
    }
}
