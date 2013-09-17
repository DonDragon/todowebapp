package ua.od.hillel.todo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.od.hillel.todo.entities.TODOEntry;
import ua.od.hillel.todo.entities.TODOList;
import ua.od.hillel.todo.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * Generic DAO
 */
@Repository
@Transactional
public class TODODao {

    /**
     * Entity Manager
     */
    @PersistenceContext
    private EntityManager entityManager;

    public List<TODOList> findTODOLists() {
        return entityManager.createQuery("SELECT l FROM TODOList l").getResultList();
    }

    public <T> T load(Class<T> clazz, Object id) {
       return entityManager.find(clazz, id);
    }

    public void update(Object o) {
        entityManager.merge(o);
    }

    public void create(Object o) {
        entityManager.persist(o);
        entityManager.flush();
    }

    public void delete(Object o) {
        entityManager.remove(o);
    }

    public void deleteById(Class clazz, Object id) {
        entityManager.remove(load(clazz, id));
    }

    public User findUserByEmail(String email) {
        return (User) entityManager.createQuery( "SELECT l FROM users l WHERE l.email=:email")
                .setParameter("email", email)
                .getSingleResult();
    }

    public List<TODOList> findTODOListsByUser(Long userId) {
        return entityManager.createQuery(
                "SELECT l FROM TODOList l WHERE l.user.id=:userId")
                .setParameter("userId", userId)
                .getResultList();
    }

    public boolean isEmailExists(String email) {
        List<User> users = entityManager.createQuery("SELECT l from users l").getResultList();

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
