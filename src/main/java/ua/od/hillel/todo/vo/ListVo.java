package ua.od.hillel.todo.vo;

import ua.od.hillel.todo.entities.TODOEntry;
import ua.od.hillel.todo.entities.TODOList;

import java.util.ArrayList;
import java.util.List;

public class ListVo {

    private String title;

    private String description;

    private List<EntryVo> entries;

    public static ListVo from(TODOList list) {
        ListVo lvo = new ListVo();
        lvo.setTitle(list.getTitle());
        lvo.setDescription(lvo.getDescription());
        lvo.setEntries(new ArrayList<EntryVo>());
        for (TODOEntry entry : list.getEntries()) {
            lvo.getEntries().add(EntryVo.from(entry));
        }
        return lvo;
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

    public List<EntryVo> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryVo> entries) {
        this.entries = entries;
    }
}