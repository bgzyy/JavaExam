package com.hand.test.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zhao'yin
 * Date 2019/7/15.
 */
public class Main {

    public static void main(String[] args) {
        InputStream in = Main.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        QueryUtil queryUtil = new QueryUtil();
        try {
            properties.load(in);
            String countryId = properties.getProperty("countryId");
            String customerId = properties.getProperty("customerId");

            queryUtil.getCityList(Integer.parseInt(countryId));
            queryUtil.getFilmWithCustomerId(Integer.parseInt(customerId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}