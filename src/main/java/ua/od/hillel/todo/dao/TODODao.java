package ua.od.hillel.todo.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.od.hillel.todo.entities.TODOEntry;
import ua.od.hillel.todo.entities.TODOList;
import ua.od.hillel.todo.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class TODODao {

    @PersistenceContext
    private EntityManager entityManager;

    private String order = "DESC";



    public List<TODOList> findTODOLists() {
        return entityManager.createQuery(
                "SELECT l FROM TODOList l").getResultList();
    }

    public <T> T load(Class<T> clazz, Long id) {
       return entityManager.find(clazz, id);
    }

    public void update(Object o) {
        entityManager.merge(o);
    }

    public void create(Object o) {
        entityManager.persist(o);
        entityManager.flush();
    }

    public void delete(Long id) {
        entityManager.remove( load(TODOList.class, id) );
    }

    public void deleteEntry(Long id) {
        entityManager.remove( load(TODOEntry.class, id) );
    }

    public List<TODOList> sortTODOLists(String param) {

        List<TODOList> resultList;

        if (param.equals("entry")) {
            resultList = entityManager.createQuery("SELECT l FROM TODOList l").getResultList();

            if (order.equals("DESC")) {
                order = "ASC";
                Collections.sort(resultList);
            }
            else {
                order = "DESC";
                Collections.sort(resultList);
                Collections.reverse(resultList);
            }

            return resultList;
        }
        else {
            if (order.equals("DESC"))
                order = "ASC";
            else
                order = "DESC";

            return entityManager.createQuery(
                    "SELECT l FROM TODOList l ORDER BY l." + param + " " +order).getResultList();
        }
    }

    public List<TODOEntry> sortTODOEntry(Long listId) {
        return entityManager.createQuery(
                "SELECT l FROM TODOEntry l WHERE l.list.id="+Long.toString(listId)+" ORDER BY l.isDone ASC").getResultList();
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

    public boolean isUsernameExists(String username) {
        List<User> users = entityManager.createQuery("SELECT l from users l").getResultList();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
