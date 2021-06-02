package dao;

import entity.User;
import net.bytebuddy.asm.Advice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements DAO {

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();;

    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        Session session = sessionFactory.openSession();
        for (Object user : session.createQuery("FROM User").list()) {
            System.out.println(user);
            System.out.println("\n================\n");
            list.add((User) user);
        }
        session.close();

        return list;
      //  return entityManager.createQuery("FROM " + User.class.getName()).getResultList();
    }
}
