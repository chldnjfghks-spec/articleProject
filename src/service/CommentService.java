package service;

import crudInterface.CrudInterface;
import dao.ArticleDAO;
import dto.CommentDto;
import entity.Comment;

public class CommentService {
    private CrudInterface dao;

    public CommentService(CrudInterface dao) {
        this.dao = dao;
    }

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
