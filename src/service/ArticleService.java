package service;

import crudInterface.CrudInterface;
import dto.ArticleDto;
import entity.Article;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleService {

    private CrudInterface dao;

    public ArticleService(CrudInterface dao) {
        this.dao = dao;
    }

    public List<ArticleDto> all() {
        return dao.all().stream()
                .map(ArticleDto::fromArticle)
                .toList();
    }

    public void newArticle(ArticleDto dto) {
        dto.setInsertedDate(LocalDateTime.now());
        Article article = ArticleDto.fromDto(dto);
        dao.newArticle(article);
    }

    public Article detail(Long id) {
        return dao.detail(id);
    }

    public boolean delete(Long id) {
        return dao.delete(id);
    }

    public void update(ArticleDto dto) {
        dto.setUpdatedDate(LocalDateTime.now());
        Article article = ArticleDto.fromDto(dto);
        dao.update(article);
    }
}