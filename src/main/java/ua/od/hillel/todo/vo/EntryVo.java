package ua.od.hillel.todo.vo;

import ua.od.hillel.todo.entities.TODOEntry;

public class EntryVo {

    private  String content;
    private Boolean isDone;

    public static EntryVo from(TODOEntry entry) {
        EntryVo evo = new EntryVo();
        evo.setDone(entry.getDone());
        evo.setContent(entry.getContent());
        return evo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}