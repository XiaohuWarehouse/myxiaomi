package com.qf.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/25 14:38
 * description:连库工具类
 */
public class DataSourceUtil {
    //创建数据源对象
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = DataSourceUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(inputStream);
            inputStream.close();
            //初始化数据
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            System.out.println("注册驱动失败：" + e.getMessage());
        }
    }

    public static DruidDataSource getDataSource() {
        return dataSource;
    }

    //获取连接
    public static Connection getConnection() {
        try {
            Connection connection = threadLocal.get();
            if (connection == null) {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            }
            return connection;
        } catch (SQLException e) {
            System.out.println("获取连接失败：" + e.getMessage());
        }
        return null;
    }

    //释放资源
    public static void closeAll(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                if (connection.getAutoCommit()) {
                    connection.close();
                    threadLocal.remove();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //编写与事务相关的方法
    public static void begin() throws SQLException {
        Connection connection = getConnection();
        if (connection != null) {
            connection.setAutoCommit(false);
        }
    }

    public static void commit() throws SQLException {
        Connection connection = getConnection();
        if (connection != null) {
            connection.commit();
        }
    }

    public static void rollback() throws SQLException {
        Connection connection = getConnection();
        if (connection != null) {
            connection.rollback();
        }
    }

    public static void close() throws SQLException {
        Connection connection = getConnection();
        if (connection != null) {
            connection.close();
            threadLocal.remove();
        }
    }

    /*public static void main(String[] args) {
        System.out.println(getConnection());
    }*/
}
