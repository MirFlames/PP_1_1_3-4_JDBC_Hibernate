package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoHibernateImpl implements UserDao {

    private static final Logger log = Logger.getLogger(UserDaoJDBCImpl.class.getName());


    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users (" +
                            "uId BIGINT AUTO_INCREMENT," +
                            "uName VARCHAR(45)," +
                            "uLastName VARCHAR(45)," +
                            "uAge TINYINT," +
                            "PRIMARY KEY (`uId`)," +
                            "UNIQUE INDEX `uId_UNIQUE` (`uId` ASC) VISIBLE)")
                    .executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            log.log(Level.SEVERE, "Exception:\n", e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("drop table if exists Users").executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            log.log(Level.SEVERE, "Exception:\n", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(User.class, id));
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User");
        List<User> users = query.list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        transaction.commit();
        session.close();
    }
}
