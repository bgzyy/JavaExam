package com.hand.test.main;

import com.hand.test.bean.City;
import com.hand.test.service.DBService;

import java.util.List;

/**
 * Created by zhao'yin
 * Date 2019/7/15.
 */
public class Main {

    DBService dbService = new DBService();

    /*
    * 根据 Country ID。返回所有属于这个 Country 的 CityID 及名称。
    * */
    public void getCityList(Integer countryId) {
        String sql = "SELECT city_id cityId, country_id countryId, city FROM city WHERE country_id = ?";
        List<City> cityList = dbService.getForList(City.class, sql, countryId);
        for (City city : cityList) {
            System.out.println(city);
        }
    }

    
}