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

    @Column
    private Long user_id;

    @Column
    private String authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
