package ua.od.hillel.todo.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.od.hillel.todo.entities.TODOList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class TODODao {

    private String order = "DESC";

    @PersistenceContext
    private EntityManager entityManager;

    public List<TODOList> findTODOLists() {
        return entityManager.createQuery(
                "SELECT l FROM TODOList l").getResultList();
    }

    public TODOList load(Long id) {
       return entityManager.find(TODOList.class, id);
    }

    public void create(Object o) {
        entityManager.persist(o);
    }

    public void delete(Long id) {
        entityManager.remove( load(id) );
    }

    public List<TODOList> sortTODOLists() {

        return entityManager.createQuery(
                "SELECT l FROM TODOList l ORDER BY l.id DESC").getResultList();
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
}
