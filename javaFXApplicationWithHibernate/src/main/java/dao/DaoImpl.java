package dao;

import entity.AccessLevel;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoImpl implements DAO {

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public ObservableList<User> findAll() {
        ObservableList<User> listUser = FXCollections.observableArrayList();

        Session session = sessionFactory.openSession();
        for (Object user : session.createQuery("FROM User").list()) {
            listUser.add((User) user);
        }
        session.close();

        return listUser;
    }

    @Override
    public ObservableList<User> findByLogin(String login) {
        ObservableList<User> listUser = FXCollections.observableArrayList();
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("FROM User WHERE login=:login");
        query.setParameter("login", login);

        for (Object user : query.getResultList()) {
            listUser.add((User) user);
        }
        session.close();

        return listUser;
    }

    @Override
    public ObservableList<User> findById(Long id) {
        ObservableList<User> listUser = FXCollections.observableArrayList();
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("FROM User WHERE id=:id");
        query.setParameter("id", id);

        for (Object user : query.getResultList()) {
            listUser.add((User) user);
        }
        session.close();

        return listUser;

    }
/*
    @Override
    public ObservableList<User> findByAccess(AccessLevel accessLevel) throws IOException, SQLException {
        ObservableList<User> userList = FXCollections.observableArrayList();

    }*/

    @Override
    public void deleteUserDao(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();;
        session.delete(user);
        transaction.commit();
        session.close();
    }
}
