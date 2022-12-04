package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.cfgxml.spi.LoadedConfig;
import org.hibernate.boot.registry.*;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {

    static final String hostName = "localhost";
    static final String dbName = "pre-project";
    static final String userName = "root";
    static final String password = "123456";
    static final String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
    //+ "&amp;serverTimezone=UTC+7"

    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static Connection getMyConnection() throws SQLException,
            ClassNotFoundException {

        return DriverManager.getConnection(connectionURL, userName,
                password);
    }



    public static SessionFactory buildSessionFactory() throws ExceptionInInitializerError {
        Configuration cfg = new Configuration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect")
                .setProperty("hibernate.connection.url", connectionURL)
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.username", userName)
                .setProperty("hibernate.connection.password", password)
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.format_sql", "true")
                .setProperty("hibernate.hbm2ddl.auto", "none")
                .setProperty("hibernate.default-schema", "pre-project")
                .addAnnotatedClass(User.class);
        try {
            return cfg.buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Initial SessionFactory failed\n" + e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}

