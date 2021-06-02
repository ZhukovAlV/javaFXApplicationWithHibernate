package dao;

import entity.AccessLevel;
import entity.User;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public interface DAO {
    ObservableList<User> findAll();
    ObservableList<User> findByLogin(String login);
    ObservableList<User> findById(Long id);
    ObservableList<User> findByAccess(AccessLevel accessLevel);
    void deleteUser(User user);
    void insertUser(String login, String password, AccessLevel accesLvl);
    void updateUser(Long id, String login, String password, AccessLevel accesLvl);
    ObservableList<AccessLevel> getAccessLevelList();
}
