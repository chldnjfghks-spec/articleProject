package dao;

import crudInterface.CrudInterface;
import db.DBConn;
import entity.Article;
import entity.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO implements CrudInterface {

    @Override
    public List<Article> all() {
        List<Article> articles = new ArrayList<>();

        String sql = "SELECT * FROM article ORDER BY id DESC";

        try {
            Connection conn = DBConn.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                Article article = new Article();
                article.setId(rs.getLong("id"));
                article.setName(rs.getString("name"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));

                articles.add(article);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return getArticleComments(articles);
    }

    private List<Article> getArticleComments(List<Article> articles) {
        String sql = "SELECT * FROM comments WHERE article_id=?";

        try {
            Connection conn = DBConn.getConnection();

            for (Article article : articles) {

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setLong(1, article.getId());

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    Comment comment = new Comment();
                    comment.setCommentId(rs.getLong("comment_id"));
                    comment.setArticleId(rs.getLong("article_id"));
                    comment.setName(rs.getString("name"));
                    comment.setContent(rs.getString("content"));

                    article.addComments(comment); // ⭐ 핵심
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return articles;
    }

    @Override
    public void newArticle(Article article) {

        String sql = "INSERT INTO article(name, title, content, inserted_date, updated_date) VALUES (?,?,?,?,?)";

        try {
            Connection conn = DBConn.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, article.getName());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getContent());

            ps.setTimestamp(4, Timestamp.valueOf(article.getInsertedDate()));
            ps.setTimestamp(5, Timestamp.valueOf(article.getUpdatedDate()));

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Article detail(Long id) {
        Article article = null;

        String sql = "SELECT * FROM article WHERE id=?";
        try {
            Connection conn = DBConn.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, id);
             ResultSet rs = ps.executeQuery();

             if (rs.next()){
                 article = new Article();
                 article.setId(rs.getLong("id"));
                 article.setName(rs.getString("name"));
                 article.setTitle(rs.getString("title"));
                 article.setContent(rs.getString("content"));

                 article.setInsertedDate(rs.getTimestamp("inserted_date").toLocalDateTime() );
                 article.setUpdatedDate(rs.getTimestamp("updated_date").toLocalDateTime()   );

             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (article != null){
            List<Article> temp = new ArrayList<>();
            temp.add(article);

            getArticleComments(temp);
        }
        return article;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM article WHERE id=?";

        int result = 0;

        try {
            Connection conn = DBConn.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, id);

            result = ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public void update(Article article) {
        String sql = "UPDATE article SET name=?, title=?, content=?," +
                " inserted_date=?, updated_date=? WHERE id=?";

        try {
            Connection conn = DBConn.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, article.getName());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getContent());

            ps.setTimestamp(4, Timestamp.valueOf(article.getInsertedDate()));
            ps.setTimestamp(5, Timestamp.valueOf(article.getUpdatedDate()));

            ps.setLong(6, article.getId());

            ps.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertComment(Comment comment) {
        String sql = "INSERT INTO comments(name, content, article_id) VALUES(?,?,?)";

        try {
            Connection conn = DBConn.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, comment.getName());
            ps.setString(2, comment.getContent());
            ps.setLong(3, comment.getArticleId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateComment(Comment comment) {
        String sql = "UPDATE comments SET content=? WHERE comment_id=?";

        try {
            Connection conn = DBConn.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, comment.getContent());
            ps.setLong(2,comment.getCommentId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteComment(Long deleteCommentId) {
        String sql = "DELETE FROM comments WHERE comment_id=?";

        try {
            Connection conn = DBConn.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, deleteCommentId);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
