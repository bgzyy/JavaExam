package com.hand.exam.test;

import com.hand.test.util.DBUtil;
import org.junit.Test;

import java.sql.Connection;

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
}