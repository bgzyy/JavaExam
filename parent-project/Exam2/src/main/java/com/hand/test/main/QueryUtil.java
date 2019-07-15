package com.hand.test.main;

import com.hand.test.bean.City;
import com.hand.test.bean.Film;
import com.hand.test.service.DBService;

import java.util.List;

/**
 * Created by zhao'yin
 * Date 2019/7/15.
 */
public class QueryUtil {

    DBService dbService = new DBService();

    /*
     * 根据 Country ID 返回所有属于这个 Country 的 CityID 及名称。
     * */
    public void getCityList(Integer countryId) {
        String sql = "SELECT city_id cityId, country_id countryId, city FROM city WHERE country_id = ?";
        List<City> cityList = dbService.getForList(City.class, sql, countryId);
        for (City city : cityList) {
            System.out.println(city);
        }
    }

    /*
     * 根据传入的 CustomerId 查询该 Customer 所租用的书籍
     * */
    public void getFilmWithCustomerId(Integer customerId) {
        String sql = "SELECT film_id filmId, title, last_update lastUpdate FROM film WHERE film_id IN (SELECT DISTINCT film_id FROM" +
                " inventory WHERE store_id IN (SELECT store_id FROM staff WHERE staff_id IN ( SELECT staff_id FROM" +
                " payment WHERE customer_id = ?))) ORDER BY last_update";
        List<Film> filmList = dbService.getForList(Film.class, sql, 1);

        for (Film film : filmList) {
            System.out.println(film);
        }
    }
}