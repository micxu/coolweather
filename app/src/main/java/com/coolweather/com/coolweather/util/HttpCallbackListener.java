package com.coolweather.com.coolweather.util;

/**
 * Created by MiaochunXu on 7/29/15.
 */
public interface HttpCallbackListener
{
    void onFinish(String response);
    void onError(Exception e);
}
