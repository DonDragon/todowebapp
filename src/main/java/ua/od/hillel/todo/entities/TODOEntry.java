package ua.od.hillel.todo.entities;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "todolist_id")
    private TODOList list;


}
