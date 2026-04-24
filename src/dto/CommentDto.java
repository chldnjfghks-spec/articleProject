package dto;


import entity.Comment;

public class CommentDto {
    private Long commentId;
    private Long articleId;
    private String name;
    private String content;

    public CommentDto(Long commertId, Long articleid, String name, String content){
        this.commentId = commertId;
        this.articleId = articleid;
        this.name = name;
        this.content = content;
    }

    public Long getCommertId() {
        return articleId;
    }

    public void setCommertId(Long commertId) {
        this.commentId = commertId;
    }

    public Long getArticleid() {
        return articleId;
    }

    public void setArticleid(Long articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public static CommentDto fromEntity(Comment comment){
        return new CommentDto(
                comment.getCommentId(),
                comment.getArticleId(),
                comment.getName(),
                comment.getContent()
        );
    }

    public static Comment fromDTO(CommentDto dto){
        Comment comment = new Comment();
        comment.setCommentId(dto.getCommertId());
        comment.setArticleId(dto.getArticleid());
        comment.setName(dto.getName());
        comment.setContent(dto.getContent());
        return comment;
    }

    @Override
    public String toString() {
        return "\t🏷️ " + commentId + "\t" + name + "\t" + content;
    }
}
