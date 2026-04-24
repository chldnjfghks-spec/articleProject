package dto;

import entity.Article;
import entity.Comment;
import entity.CommonField;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleDto extends CommonField {
    private Long id;
    private String name;
    private String title;
    private String content;
    private List<CommentDto> commentList = new ArrayList<>();

    public ArticleDto(Long id, String name, String title, String content){
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
    }
    public ArticleDto(Long id, String name, String title, String content, LocalDateTime insertedDate ){
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
        setInsertedDate(insertedDate);
    }
    public ArticleDto(Long id, String name, String title, String content, LocalDateTime insertedDate,
                      LocalDateTime updatedDate){
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
        setInsertedDate(insertedDate);
        setUpdatedDate(updatedDate);
    }
    public ArticleDto(Long id, String name, String title, String content, LocalDateTime insertedDate,
                      LocalDateTime updatedDate, List<Comment> comments){
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
        setInsertedDate(insertedDate);
        setUpdatedDate(updatedDate);

        if (comments != null){
            for (Comment c : comments){
                this.commentList.add(CommentDto.fromEntity(c));
            }
        }
    }

    public Long getId() {
        return id;
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

    public List<CommentDto> getCommentList() {
        return commentList;
    }
    public static ArticleDto makeArticleDto(Long id, String name, String title, String content,
                                             LocalDateTime insertedDate){
        ArticleDto dto = new ArticleDto(id, name, title, content, insertedDate);
        return dto;
    }

    public static ArticleDto fromArticle(Article article){
        return new ArticleDto(
                article.getId(),
                article.getName(),
                article.getTitle(),
                article.getContent(),
                article.getInsertedDate(),
                article.getUpdatedDate(),
                article.getCommentList()
        );
    }
    public static Article fromDto(ArticleDto dto){

        Article article = new Article();
        article.setId(dto.getId());
        article.setName(dto.getName());
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setInsertedDate(dto.getInsertedDate());
        article.setUpdatedDate(dto.getUpdatedDate());

        if (dto.getCommentList() != null) {
            for (CommentDto c : dto.getCommentList()) {
                article.addComments(CommentDto.fromDTO(c));
            }
        }

        return article;
    }
}