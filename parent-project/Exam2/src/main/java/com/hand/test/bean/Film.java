package com.hand.test.bean;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by zhao'yin
 * Date 2019/7/15.
 */
public class Film {

    private Integer filmId;
    private String title;
    private Timestamp lastUpdate;

    public Film() {
    }

    public Film(Integer filmId, String title, Timestamp lastUpdate) {
        this.filmId = filmId;
        this.title = title;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", title='" + title + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
