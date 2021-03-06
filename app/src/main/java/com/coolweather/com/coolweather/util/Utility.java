package com.coolweather.com.coolweather.util;

import android.text.TextUtils;

import com.coolweather.com.coolweather.db.CoolWeatherDB;
import com.coolweather.com.coolweather.model.City;
import com.coolweather.com.coolweather.model.County;
import com.coolweather.com.coolweather.model.Province;

/**
 * Created by MiaochunXu on 8/4/15.
 */
public class Utility
{

    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, String response)
    {
        if (!TextUtils.isEmpty(response))
        {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0)
            {
                for (String p : allProvinces)
                {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);

                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }


    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId)
    {
        if (!TextUtils.isEmpty(response))
        {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0)
            {
                for (String c : allCities)
                {
                    String[] array = response.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);

                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }

        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId)
    {
        if (!TextUtils.isEmpty(response))
        {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0)
            {
                for (String c : allCounties)
                {
                    String[] array = response.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);

                    coolWeatherDB.saveCounty(county);
                }
            }
            return true;
        }

        return false;
    }

}
