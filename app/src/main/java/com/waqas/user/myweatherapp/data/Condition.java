package com.waqas.user.myweatherapp.data;

import org.json.JSONObject;

/**
 * Created by user on 5/18/2016.
 */
public class Condition implements JSONPopulator {

    private int code;
    private int temperature;
    private String description;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public void populate(JSONObject jsonObject) {
        code = jsonObject.optInt("code");
        temperature = jsonObject.optInt("temp");
        description = jsonObject.optString("text");

    }
}
