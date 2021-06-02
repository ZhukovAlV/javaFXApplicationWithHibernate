package dao;

import entity.AccessLevel;
import entity.User;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DAO {
    ObservableList<User> findAll();
    ObservableList<User> findByLogin(String login) throws IOException, SQLException;
    ObservableList<User> findById(Long id) throws IOException, SQLException;
    /*  ObservableList<User> findByAccess(AccessLevel accessLevel) throws IOException, SQLException;*/
}
