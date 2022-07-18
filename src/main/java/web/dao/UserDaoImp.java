package web.dao;

import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImp (EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUser(User user){
        entityManager.remove(entityManager.find(User.class, user.getId()));
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUser(Long id) {
        TypedQuery<User> query = entityManager.createQuery("FROM User WHERE id = :id", User.class);
        query.setParameter("id", id);
        return query.getResultList().get(0);
    }
}
