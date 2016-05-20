package com.waqas.user.myweatherapp.service;

import com.waqas.user.myweatherapp.data.Channel;

/**
 * Created by user on 5/18/2016.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);

}
