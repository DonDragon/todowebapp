package ua.od.hillel.todo.entities;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Table;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: altair
 * Date: 06.09.13
 * Time: 23:14
 * To change this template use File | Settings | File Templates.
 */
@Entity(name="users")
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique=true)
    private String username;

    @Column
    private Integer age;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Integer enabled;

    @OneToMany(mappedBy="user")
    private List<TODOList> todoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public List<TODOList> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<TODOList> todoList) {
        this.todoList = todoList;
    }
}
