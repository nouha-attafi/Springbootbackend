package tn.esprit._4twin6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit._4twin6.entities.Article;
import tn.esprit._4twin6.enums.TypeArticle;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    // 1. Trouver tous les articles d'un nom spécifique
    @Query("SELECT a FROM Article a WHERE a.nomArticle = :nomArticle")
    List<Article> findByNomArticle(@Param("nomArticle") String nomArticle);

    // 2. Trouver les articles par prix exact
    @Query("SELECT a FROM Article a WHERE a.prixArticle = :prixArticle")
    List<Article> findByPrixArticle(@Param("prixArticle") Double prixArticle);

    // 3. Compter le nombre d'articles avec un nom spécifique
    @Query("SELECT COUNT(a) FROM Article a WHERE a.nomArticle = :nomArticle")
    long countByNomArticle(@Param("nomArticle") String nomArticle);

    // 4. Supprimer tous les articles d'un nom spécifique
    @Modifying
    @Query("DELETE FROM Article a WHERE a.nomArticle = :nomArticle")
    void deleteByNomArticle(@Param("nomArticle") String nomArticle);

    // 5. Trouver les articles d'un nom avec un prix spécifique
    @Query("SELECT a FROM Article a WHERE a.nomArticle = :nomArticle AND a.prixArticle = :prixArticle")
    List<Article> findByNomArticleAndPrixArticle(@Param("nomArticle") String nomArticle, @Param("prixArticle") Double prixArticle);

    // 6. Trouver les articles dont le nom contient un mot, insensible à la casse DU NOM (pas du type, car enum)
    // Changé : Exact sur TypeArticle + IgnoreCase seulement sur nomArticle (String)
    @Query("SELECT a FROM Article a WHERE a.typeArticle = :typeArticle AND LOWER(a.nomArticle) LIKE LOWER(CONCAT('%', :mot, '%'))")
    List<Article> findByTypeArticleAndNomArticleContainingIgnoreCase(@Param("typeArticle") TypeArticle typeArticle, @Param("mot") String mot);

    // 7. Trouver les articles situés dans une liste de types
    @Query("SELECT a FROM Article a WHERE a.typeArticle IN :types")
    List<Article> findByTypeArticleIn(@Param("types") List<TypeArticle> types);

    // 8. Trouver les articles avec un prix dans une plage spécifique
    @Query("SELECT a FROM Article a WHERE a.prixArticle BETWEEN :min AND :max")
    List<Article> findByPrixArticleBetween(@Param("min") Double min, @Param("max") Double max);

    // 9. Trouver les articles avec un prix supérieur au prix passé en paramètre
    @Query("SELECT a FROM Article a WHERE a.prixArticle > :prixArticle")
    List<Article> findByPrixArticleGreaterThan(@Param("prixArticle") Double prixArticle);

    // 10. Trouver les articles avec un prix supérieur ou égal au prix passé en paramètre
    @Query("SELECT a FROM Article a WHERE a.prixArticle >= :prixArticle")
    List<Article> findByPrixArticleGreaterThanEqual(@Param("prixArticle") Double prixArticle);

    // 11. Trouver les articles avec un prix inférieur au prix passé en paramètre
    @Query("SELECT a FROM Article a WHERE a.prixArticle < :prixArticle")
    List<Article> findByPrixArticleLessThan(@Param("prixArticle") Double prixArticle);

    // 12. Trouver les articles avec un prix inférieur ou égal au prix passé en paramètre
    @Query("SELECT a FROM Article a WHERE a.prixArticle <= :prixArticle")
    List<Article> findByPrixArticleLessThanEqual(@Param("prixArticle") Double prixArticle);

    // 13. Trouver les articles dont le nom commence par, dans un type, triés par prix
    @Query("SELECT a FROM Article a WHERE a.nomArticle LIKE CONCAT(:nomStart, '%') AND a.typeArticle = :typeArticle ORDER BY a.prixArticle")
    List<Article> findByNomArticleStartingWithAndTypeArticleOrderByPrixArticle(@Param("nomStart") String nomStart, @Param("typeArticle") TypeArticle typeArticle);

    // 14. Trouver les articles dont le nom commence par une chaîne spécifique
    @Query("SELECT a FROM Article a WHERE a.nomArticle LIKE CONCAT(:nomStart, '%')")
    List<Article> findByNomArticleStartingWith(@Param("nomStart") String nomStart);

    // 15. Trouver les articles dont le nom se termine par une terminaison spécifique
    @Query("SELECT a FROM Article a WHERE a.nomArticle LIKE CONCAT('%', :nomEnd)")
    List<Article> findByNomArticleEndingWith(@Param("nomEnd") String nomEnd);

    // 16. Trouver les articles où le champ nom est null
    @Query("SELECT a FROM Article a WHERE a.nomArticle IS NULL")
    List<Article> findByNomArticleIsNull();

    // 17. Trouver les articles où le type n'est pas null
    @Query("SELECT a FROM Article a WHERE a.typeArticle IS NOT NULL")
    List<Article> findByTypeArticleIsNotNull();

    // AJOUT : Méthode pour fetch Article avec Promotions jointes (pour cascade delete)
    @Query("SELECT a FROM Article a LEFT JOIN FETCH a.promotions WHERE a.idArticle = :id")
    Optional<Article> findByIdWithPromotions(@Param("id") Long id);






    @Query("SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.promotions p WHERE " +
            "p.dateDebutPromotion <= :endOfMonth AND " +
            "(p.dateFinPromotion IS NULL OR p.dateFinPromotion >= :startOfMonth)")
    List<Article> findPromotedArticlesForMonth(@Param("startOfMonth") LocalDate startOfMonth,
                                               @Param("endOfMonth") LocalDate endOfMonth);



}