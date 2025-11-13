package tn.esprit._4twin6.services.article;

import tn.esprit._4twin6.dto.ArticleDTO.ArticleRequest;
import tn.esprit._4twin6.dto.ArticleDTO.ArticleResponse;
import tn.esprit._4twin6.entities.Article;
import java.util.List;

public interface IArticleServiceImpl {

    ArticleResponse addArticle(ArticleRequest request);

    List<ArticleResponse> saveArticles(List<ArticleRequest> requests);

    ArticleResponse selectArticleById(long id);

    List<ArticleResponse> selectAllArticles();

    void deleteArticle(long id);

    void deleteAllArticles();

    long countingArticles();

    boolean verifArticleById(long id);
}
