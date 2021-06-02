package dao;

import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DaoImpl implements DAO {

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();;

    public ObservableList<User> findAll() {
        ObservableList<User> list = FXCollections.observableArrayList();

        Session session = sessionFactory.openSession();
        for (Object user : session.createQuery("FROM User").list()) {
            list.add((User) user);
        }
        session.close();

        return list;
    }
}
