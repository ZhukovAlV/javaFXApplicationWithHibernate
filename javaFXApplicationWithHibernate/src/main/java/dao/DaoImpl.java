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
import java.util.List;

public class DaoImpl implements DAO {

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();;

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List users = session.createQuery("FROM User").list();
        for (Object user : users) {
            System.out.println(user);
            System.out.println("\n================\n");
        }
        session.close();
        return users;
      //  return entityManager.createQuery("FROM " + User.class.getName()).getResultList();
    }
}
