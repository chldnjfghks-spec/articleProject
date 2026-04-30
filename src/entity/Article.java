package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Article {
    private Long id;
    private String name;
    private String title;
    private String content;
    private LocalDateTime insertedDate;
    private LocalDateTime updatedDate;

    private List<Comment> commentList = new ArrayList<>();


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getInsertedDate() {
        return insertedDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setInsertedDate(LocalDateTime insertedDate) {
        this.insertedDate = insertedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public void addComments(Comment comment) {
        commentList.add(comment);
    }
    public List<Comment> getCommentList() {
        return commentList;
    }
}
