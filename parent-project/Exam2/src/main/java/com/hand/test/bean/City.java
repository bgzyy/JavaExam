package com.hand.test.bean;

/**
 * Created by zhao'yin
 * Date 2019/7/15.
 */
public class City {
    private Integer cityId;
    private Integer countryId;
    private String city;

    public City() {
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", countryId=" + countryId +
                ", city='" + city + '\'' +
                '}';
    }

    public City(Integer cityId, Integer countryId, String city) {
        this.cityId = cityId;
        this.countryId = countryId;
        this.city = city;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
