package ua.od.hillel.todo.entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name="TODOList")
public class TODOList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany(mappedBy = "list")
    private List<TODOEntry> entries;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TODOEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<TODOEntry> entries) {
        this.entries = entries;
    }
}
