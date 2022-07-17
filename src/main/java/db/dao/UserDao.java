package db.dao;

import db.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    void removeUser(User user);
    List<User> listUsers();
    void editUser(User user);
}
