package db.dao;

import db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Autowired
    public UserDaoImp (EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void add(User user) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void removeUser(User user){
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(User.class, user.getId()));
        entityManager.getTransaction().commit();
    }

    @Override
    public List<User> listUsers() {
        entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public void editUser(User user) {
        if (user.getId() != null) {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            User userTemp = entityManager.find(User.class, user.getId());
            if (userTemp != null) {
                userTemp.setFirstName(user.getFirstName());
                userTemp.setLastName(user.getLastName());
                userTemp.setEmail(user.getEmail());
                entityManager.merge(userTemp);
            } else {
                entityManager.merge(user);
            }
            entityManager.getTransaction().commit();
        }
    }
}
