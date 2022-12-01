package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {

    private static final Logger log = Logger.getLogger(UserDaoJDBCImpl.class.getName());

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getMyConnection()) {
            connection.createStatement().execute(
                    "CREATE TABLE Users (" +
                            "uId BIGINT AUTO_INCREMENT," +
                            "uName VARCHAR(45)," +
                            "uLastName VARCHAR(45)," +
                            "uAge SMALLINT," +
                            "PRIMARY KEY (`uId`)," +
                            "UNIQUE INDEX `uId_UNIQUE` (`uId` ASC) VISIBLE)"
            );
        } catch (SQLException | ClassNotFoundException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getMyConnection()) {
            connection.createStatement().execute("DROP TABLE Users");
        } catch (SQLException | ClassNotFoundException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getMyConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users (uName, uLastName, uAge) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

            log.log(Level.INFO, "User с именем – {0} добавлен в базу данных", name);
        } catch (SQLException | ClassNotFoundException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getMyConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE uId = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getMyConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT uName, uLastName, uAge FROM Users");
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getString("uName"),
                        resultSet.getString("uLastName"),
                        resultSet.getByte("uAge")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getMyConnection()) {
            connection.createStatement().execute("DELETE FROM Users");
        } catch (SQLException | ClassNotFoundException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }
}
