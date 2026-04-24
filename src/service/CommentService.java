package service;

import crudInterface.CrudInterface;
import dao.ArticleDAO;
import dto.CommentDto;
import entity.Comment;

public class CommentService {
    CrudInterface dao = new ArticleDAO();

    public  void commentAdd(CommentDto comment){
        Comment entity = CommentDto.fromDTO(comment);
        dao.insertComment(entity);
    }

    public void commentUpdate(CommentDto comment){
        Comment entity = CommentDto.fromDTO(comment);
        dao.updateComment(entity);
    }

    public void commentDelete(Long deleteCommentId){
        dao.deleteComment(deleteCommentId);
    }

}
