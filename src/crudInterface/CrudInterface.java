package crudInterface;

import entity.Article;
import entity.Comment;

import java.util.List;

public interface CrudInterface {
    List<Article> all();
    void    newArticle(Article article);
    Article detail(Long id);
    boolean delete(Long id);
    void    update(Article article);

    // 댓글
    void insertComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(Long deleteCommentId);
}
