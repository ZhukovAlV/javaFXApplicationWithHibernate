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
import java.time.LocalDateTime;

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

    @Override
    public ObservableList<User> findByAccess(AccessLevel accessLevel) {
        ObservableList<User> listUser = FXCollections.observableArrayList();
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("FROM User WHERE accessLvl=:accessLevel");
        query.setParameter("accessLevel", accessLevel);

        for (Object user : query.getResultList()) {
            listUser.add((User) user);
        }
        session.close();

        return listUser;
    }

    public ObservableList<AccessLevel> getAccessLevelList() {
        ObservableList<AccessLevel> listAccessLevel = FXCollections.observableArrayList();
        Session session = sessionFactory.openSession();

        for (Object accessLevel : session.createQuery("FROM AccessLevel").list()) {
            listAccessLevel.add((AccessLevel) accessLevel);
        }
        session.close();

        return listAccessLevel;
    }

    @Override
    public void deleteUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();;
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public void insertUser(String login, String password, AccessLevel accesLvl) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User(login, password, accesLvl, LocalDateTime.now());
        session.save(user);

        transaction.commit();
        session.close();
    }

    public void updateUser(Long id, String login, String password, AccessLevel accesLvl) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User(id, login, password, accesLvl, findById(id).get(0).getDateOfCreation(), LocalDateTime.now());
        session.update(user);

        transaction.commit();
        session.close();
    }
}
