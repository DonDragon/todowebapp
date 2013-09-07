package ua.od.hillel.todo.entities;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: altair
 * Date: 07.09.13
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="userRoles")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
