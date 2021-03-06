package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void removeUser(User user);
    List<User> listUsers();
    void editUser(User user);
    User getUser(Long id);
}
