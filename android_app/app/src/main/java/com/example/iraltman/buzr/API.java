package com.example.iraltman.buzr;

import com.example.iraltman.buzr.rest.RestRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

/**
 * Created by oranja on 19/05/16.
 */
public class API {
    private String baseUrl;

    public API(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<Deal> getDeals(int categoryId) {
        final String url = this.buildUrl("/api/deals/" + categoryId);
        List<Deal> deals = new LinkedList<>();

        JSONObject deals_json;
        try {
            deals_json = new RestRequest(url).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return deals;   // empty
        } catch (ExecutionException e) {
            e.printStackTrace();
            return deals;   // empty
        }
        if (! isOk(deals_json)) {
            return deals;   // empty
        }

        try {
            JSONArray deals_data = deals_json.getJSONArray("data");
            for (int deal_index = 0; deal_index < deals_data.length(); deal_index++) {
                deals.add(new Deal(deals_data.getJSONObject(deal_index)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return deals;
        }

        return deals;
    }

    public List<Deal> getDeals(String locationId) {
        final String url = this.buildUrl("/api/nearby_deals/" + locationId);
        List<Deal> deals = new LinkedList<>();

        JSONObject deals_json;
        try {
            deals_json = new RestRequest(url).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return deals;   // empty
        } catch (ExecutionException e) {
            e.printStackTrace();
            return deals;   // empty
        }
        if (! isOk(deals_json)) {
            return deals;   // empty
        }

        try {
            JSONArray deals_data = deals_json.getJSONArray("data");
            for (int deal_index = 0; deal_index < deals_data.length(); deal_index++) {
                deals.add(new Deal(deals_data.getJSONObject(deal_index)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return deals;
        }

        return deals;
    }

    private boolean isOk(JSONObject response) {
        try {
            return response.getBoolean("success") && response.has("data");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String buildUrl(String path) {
        return this.baseUrl + path;
    }
}
