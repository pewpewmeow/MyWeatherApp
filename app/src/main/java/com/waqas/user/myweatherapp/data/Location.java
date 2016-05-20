package com.waqas.user.myweatherapp.data;

import org.json.JSONObject;

/**
 * Created by user on 5/18/2016.
 */
public class Location implements JSONPopulator{
    private String city, country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void populate(JSONObject jsonObject) {
        city = jsonObject.optString("city");
        country = jsonObject.optString("country");
    }
}
