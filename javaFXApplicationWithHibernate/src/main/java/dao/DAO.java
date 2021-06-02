package dao;

import entity.User;
import javafx.collections.ObservableList;

import java.util.List;

public interface DAO {
    ObservableList<User> findAll();
}
