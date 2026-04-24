package repository;

import crudInterface.CrudInterface;
import entity.Article;
import entity.Comment;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository implements CrudInterface {

    private static Long articleId = 1L;
    private static Long commentId = 1L;
    private static List<Article> list = new ArrayList<>();

    @Override
    public List<Article> all() {
        return list;
    }

    @Override
    public void newArticle(Article article) {
        article.setId(articleId++);
        list.add(article);
    }

    @Override
    public Article detail(Long id) {
        for (Article article : list) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return list.removeIf(article -> article.getId() == id);
    }

    @Override
    public void update(Article article) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == article.getId()) {
                list.set(i, article);
                return;
            }
        }
    }

    @Override
    public void insertComment(Comment comment) {
        Article article = detail(comment.getArticleId());

        if (article != null) {
            comment.setCommentId(commentId++);
            article.addComments(comment);
        }
    }

    @Override
    public void updateComment(Comment comment) {
        for (Article article : list) {
            for (Comment c : article.getCommentList()) {
                if (c.getCommentId().equals(comment.getCommentId())) {
                    c.setContent(comment.getContent());
                    return;
                }
            }
        }
    }

    @Override
    public void deleteComment(Long deleteCommentId) {
        for (Article article : list) {
            article.getCommentList()
                    .removeIf(c -> c.getCommentId().equals(deleteCommentId));
        }
    }
}
