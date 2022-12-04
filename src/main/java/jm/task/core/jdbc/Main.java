package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();

        userService.saveUser("Vladimir", "Ivanov", (byte) 22);
        userService.saveUser("Vladimir2", "Ivanov", (byte) 23);
        userService.saveUser("Vladimir3", "Ivanov", (byte) 24);
        userService.saveUser("Vladimir4", "Ivanov", (byte) 25);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
