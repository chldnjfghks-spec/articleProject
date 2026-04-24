package entity;

public class Comment {

    private Long commentId;
    private Long articleId;
    private String name;
    private String content;

    // getter
    public Long getCommentId() {
        return commentId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    // setter
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }
}