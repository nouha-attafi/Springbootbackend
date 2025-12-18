package tn.esprit._4twin6.services.article;

import tn.esprit._4twin6.dto.ArticleDTO.ArticleRequest;
import tn.esprit._4twin6.dto.ArticleDTO.ArticleResponse;
import tn.esprit._4twin6.entities.Article;
import tn.esprit._4twin6.enums.TypeArticle;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface IArticleService {

    // Méthodes CRUD existantes
    ArticleResponse addArticle(ArticleRequest request);
    List<ArticleResponse> saveArticles(List<ArticleRequest> requests);
    ArticleResponse selectArticleById(long id);
    List<ArticleResponse> selectAllArticles();
    void deleteArticle(long id);
    void deleteAllArticles();
    long countingArticles();
    boolean verifArticleById(long id);

    // Nouvelles méthodes pour ArticleRepository
    // 1. Trouver tous les articles d'un nom spécifique
    List<ArticleResponse> getArticlesByNomArticle(String nomArticle);

    // 2. Trouver les articles par prix exact
    List<ArticleResponse> getArticlesByPrixArticle(Double prixArticle);

    // 3. Compter le nombre d'articles avec un nom spécifique
    long countArticlesByNomArticle(String nomArticle);

    // 4. Supprimer tous les articles d'un nom spécifique
    void deleteArticlesByNomArticle(String nomArticle);

    // 5. Trouver les articles d'un nom avec un prix spécifique
    List<ArticleResponse> getArticlesByNomArticleAndPrixArticle(String nomArticle, Double prixArticle);

    // 6. Trouver les articles dont le nom contient un mot, insensible à la casse du nom (type exact)
    List<ArticleResponse> getArticlesByTypeArticleAndNomArticleContainingIgnoreCase(TypeArticle typeArticle, String mot);

    // 7. Trouver les articles situés dans une liste de types
    List<ArticleResponse> getArticlesByTypeArticlesIn(List<TypeArticle> types);

    // 8. Trouver les articles avec un prix dans une plage spécifique
    List<ArticleResponse> getArticlesByPrixArticleBetween(Double min, Double max);

    // 9. Trouver les articles avec un prix supérieur au prix passé en paramètre
    List<ArticleResponse> getArticlesByPrixArticleGreaterThan(Double prixArticle);

    // 10. Trouver les articles avec un prix supérieur ou égal au prix passé en paramètre
    List<ArticleResponse> getArticlesByPrixArticleGreaterThanEqual(Double prixArticle);

    // 11. Trouver les articles avec un prix inférieur au prix passé en paramètre
    List<ArticleResponse> getArticlesByPrixArticleLessThan(Double prixArticle);

    // 12. Trouver les articles avec un prix inférieur ou égal au prix passé en paramètre
    List<ArticleResponse> getArticlesByPrixArticleLessThanEqual(Double prixArticle);

    // 13. Trouver les articles dont le nom commence par, dans un type, triés par prix
    List<ArticleResponse> getArticlesByNomArticleStartingWithAndTypeArticleOrderByPrixArticle(String nomStart, TypeArticle typeArticle);

    // 14. Trouver les articles dont le nom commence par une chaîne spécifique
    List<ArticleResponse> getArticlesByNomArticleStartingWith(String nomStart);

    // 15. Trouver les articles dont le nom se termine par une terminaison spécifique
    List<ArticleResponse> getArticlesByNomArticleEndingWith(String nomEnd);

    // 16. Trouver les articles où le champ nom est null
    List<ArticleResponse> getArticlesByNomArticleIsNull();

    // 17. Trouver les articles où le type n'est pas null
    List<ArticleResponse> getArticlesByTypeArticleIsNotNull();

    // AJOUTS : Signatures pour cascade methods (ajout et suppression avec Promotions)
    ArticleResponse addArticleWithCascade(ArticleRequest request);

    void deleteArticleWithCascade(Long id);

    Optional<Article> findArticleByIdWithPromotions(Long id);



    List<ArticleResponse> findPromotedArticlesForMonth(YearMonth month);

}