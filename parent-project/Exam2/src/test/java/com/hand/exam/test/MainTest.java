package com.hand.exam.test;

import com.hand.test.bean.City;
import com.hand.test.bean.Film;
import com.hand.test.service.DBService;
import com.hand.test.util.DBUtil;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * Created by zhao'yin
 * Date 2019/7/15.
 */
public class MainTest {

    @Test
    public void testConnection() {
        Connection connection;
        try {
            connection = DBUtil.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        String sql = "SELECT city_id cityId, country_id countryId, city FROM city WHERE country_id = ?";
        DBService dbService = new DBService();
        List<City> cityList= dbService.getForList(City.class, sql, 2);
        for (City city : cityList) {
            System.out.println(city);
        }
    }

    @Test
    public void getList() {
        DBService dbService = new DBService();
        String sql = "SELECT film_id filmId, title, last_update lastUpdate FROM film WHERE film_id IN (SELECT DISTINCT film_id FROM" +
                " inventory WHERE store_id IN (SELECT store_id FROM staff WHERE staff_id IN ( SELECT staff_id FROM" +
                " payment WHERE customer_id = ?))) ORDER BY last_update";
        List<Film> cityList = dbService.getForList(Film.class, sql, 1);

        for (Film film : cityList) {
            System.out.println(film);
        }
    }
}