package com.hand.test.service;

import com.hand.test.util.DBUtil;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by zhao'yin
 * Date 2019/7/15.
 */
public class DBService {

    public <T> T get(Class<T> clazz, String sql, Object ... args) {
        T entity = null;

        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        ResultSetMetaData resultSetMetaData;
//        存储列名以及列值
        Map<String, Object> map = new HashMap<String, Object>();

        connection = DBUtil.getConnection();
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();

            if (resultSet.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    map.put(resultSetMetaData.getColumnLabel(i), resultSet.getObject(i));
                }
            }

//            判断 map 中是否有值，若有则利用反射创建对象，并为之赋值
            if (map.size() > 0) {
                entity = clazz.newInstance();
//                利用for 循环为每一个变量赋值
                for (Map.Entry<String, Object> entry: map.entrySet()) {
                    String fieldName = entry.getKey();
                    Object fieldValue = entry.getValue();

                    Field field = clazz.getDeclaredField(fieldName);
//                    打破封装
                    field.setAccessible(true);
                    field.set(entity, fieldValue);
                }
            }

            System.out.printf(String.valueOf(entity));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return entity;
    }

    public <T> List<T> getForList(Class<T> clazz, String sql, Object... args) {
//       存取所有对象
        List<T> entities = new ArrayList<T>();

        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        connection = DBUtil.getConnection();
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();

//        存取查询到的多条记录
            List<Map<String, Object>> listMap = handleResultSetToMapList(resultSet);
//        存取对象集合
            entities = changeMapListToBeanList(clazz, listMap);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            DBUtil.releaseConnection(connection, preparedStatement, resultSet);
        }

        return entities;
    }


    private <T> List<T> changeMapListToBeanList(Class<T> clazz, List<Map<String, Object>> listMap) throws InstantiationException, IllegalAccessException {

        List<T> entities = new ArrayList<T>();
        T entity2;
        if (listMap.size() > 0) {

            Iterator<Map<String, Object>> iterator = listMap.iterator();

            while (iterator.hasNext()) {
                entity2 = clazz.newInstance();
                for (Map.Entry<String, Object> entry : iterator.next().entrySet()) {
                    String fieldName = entry.getKey();
                    Object fieldVal = entry.getValue();
                    setFieldVal(entity2, fieldName, fieldVal);
                }
                entities.add(entity2);
            }
        }
        return entities;
    }

    //    处理结果集，将查询到的每一条结果集存入到 List 中
    private List<Map<String, Object>> handleResultSetToMapList(ResultSet resultSet) throws SQLException {

        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        ResultSetMetaData resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();
//        存取查询结果中的一条记录
        Map<String, Object> map;
        while (resultSet.next()) {
//                每次都将 map 对象 new 一下，这样每次就是不同的值，不会出现一直存取第一个值
            map = new HashMap<>();
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                String colLabel = resultSetMetaData.getColumnLabel(i + 1);
                Object colVal = resultSet.getObject(i + 1);
                map.put(colLabel, colVal);
            }
            mapList.add(map);
        }
        return mapList;
    }

    public void setFieldVal(Object obj, String fieldName, Object fieldVal) {
        Field field;
        try {
//            使用反射赋值
            field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, fieldVal);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}