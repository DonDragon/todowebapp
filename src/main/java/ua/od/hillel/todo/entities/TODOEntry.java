package ua.od.hillel.todo.entities;

import javax.persistence.*;

/**
 * Entry
 */
@Entity
@Table(name="TODOEntry")
public class TODOEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String content;

    @Column
    private Integer position;

    @Column
    private Boolean isDone = false;

    @ManyToOne
    @JoinColumn(name = "todolist_id")
    private TODOList list;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public TODOList getList() {
        return list;
    }

    public void setList(TODOList list) {
        this.list = list;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}
