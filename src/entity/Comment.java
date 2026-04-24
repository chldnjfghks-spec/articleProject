package entity;

public class Comment {
    public long getCommentId;
    private String name;
    private String content;
    private Long ArticleId;
    private Long CommentId;



    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public long getArticleId() {
        return ArticleId;
    }
    public long getCommentId(){
        return CommentId;
    }

    public void setCommentId(long commentId) {
    }

    public void setArticleId(long articleId) {
    }

    public void setName(String name) {
    }

    public void setContent(String content) {
    }
}
