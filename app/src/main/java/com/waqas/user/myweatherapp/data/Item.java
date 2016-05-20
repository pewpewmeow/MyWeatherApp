package com.waqas.user.myweatherapp.data;

import org.json.JSONObject;

/**
 * Created by user on 5/18/2016.
 */
public class Item implements JSONPopulator{
    public Condition condition;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public void populate(JSONObject jsonObject) {
        condition = new Condition();
        condition.populate(jsonObject.optJSONObject("condition"));
    }
}
