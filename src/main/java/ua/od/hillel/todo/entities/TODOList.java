package ua.od.hillel.todo.entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

/**
 * List of logical grouped entries
 */
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

    @Column
    private Boolean isChecked = false;

    @OneToMany(mappedBy = "list", cascade = CascadeType.REMOVE)
    private List<TODOEntry> entries;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

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

    public Integer getEntriesCount() {
        return getEntries().size();
    }

}
