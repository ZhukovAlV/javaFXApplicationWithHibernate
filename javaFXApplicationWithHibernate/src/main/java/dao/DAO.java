package dao;

import entity.User;

import java.util.List;

public interface DAO {
    List<User> findAll();
}
