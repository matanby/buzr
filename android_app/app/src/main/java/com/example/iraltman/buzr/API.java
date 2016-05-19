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

    public String buildUrl(String path) {
        return this.baseUrl + path;
    }

    public List<Category> getCategories() {
        final String url = this.buildUrl("/api/categories");
        List<Category> categories = new LinkedList<>();

        JSONObject categories_json;
        try {
            categories_json = new RestRequest(url).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return categories;   // empty
        } catch (ExecutionException e) {
            e.printStackTrace();
            return categories;   // empty
        }
        if (! isOk(categories_json)) {
            return categories;   // empty
        }

        try {
            JSONArray categories_data = categories_json.getJSONArray("data");
            for (int category_index = 0; category_index < categories_data.length(); category_index++) {
                categories.add(new Category(categories_data.getJSONObject(category_index)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return categories;
        }

        return categories;
    }

    public List<Store> getStores() {
        final String url = this.buildUrl("/api/stores");
        List<Store> stores = new LinkedList<>();

        JSONObject stores_json;
        try {
            stores_json = new RestRequest(url).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return stores;   // empty
        } catch (ExecutionException e) {
            e.printStackTrace();
            return stores;   // empty
        }
        if (! isOk(stores_json)) {
            return stores;   // empty
        }

        try {
            JSONArray stores_data = stores_json.getJSONArray("data");
            for (int store_index = 0; store_index < stores_data.length(); store_index++) {
                stores.add(new Store(stores_data.getJSONObject(store_index)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return stores;
        }

        return stores;
    }

    public List<Deal> getDeals(int categoryId) {
        String url;
        if (categoryId == 1) {
            url = this.buildUrl("/api/deals");
        } else {
            url = this.buildUrl("/api/deals/" + categoryId);
        }
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

    public List<Deal> getNearbyDeals(String locationId) {
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
            return (response != null)
                    && response.getBoolean("success")
                    && response.has("data");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
