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


    public void setId(long id) {
    }

    public void setName(String name) {
    }

    public void setTitle(String title) {
    }

    public void setContent(String content) {
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

    public void setInsertedDate() {
    }

    public void setInsertedDate(LocalDateTime insertedDate) {
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
    }

    public long getId() {
        return id;
    }

    public void addComments(Comment comment) {
    }
}
