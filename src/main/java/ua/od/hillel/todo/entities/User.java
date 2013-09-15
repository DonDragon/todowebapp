package ua.od.hillel.todo.entities;

import org.hibernate.validator.constraints.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name="users")
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(nullable = false, unique=true)
    private String username;

    @NotNull
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Range(min = 6, max = 100)
    @Column
    private Integer age;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    @Column
    private String password;

    @Column
    private Integer enabled;


    @OneToMany(mappedBy="user")
    private List<TODOList> todoList;

    public User() {
        // empty
    }

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

    @Override
    public String toString() {
        return getUsername();
    }
}
