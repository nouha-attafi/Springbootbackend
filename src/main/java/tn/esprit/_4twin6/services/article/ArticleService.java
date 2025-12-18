package tn.esprit._4twin6.services.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit._4twin6.dto.ArticleDTO.ArticleRequest;
import tn.esprit._4twin6.dto.ArticleDTO.ArticleResponse;
import tn.esprit._4twin6.entities.Article;
import tn.esprit._4twin6.entities.Promotion;
import tn.esprit._4twin6.enums.TypeArticle;
import tn.esprit._4twin6.mapper.IArticleMapper;
import tn.esprit._4twin6.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Qualifier;


import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleService implements IArticleService
 {

    private final ArticleRepository articleRepo;

     @Qualifier("IArticleMapperImpl")
     private final IArticleMapper articleMapper;

    @Override
    public ArticleResponse addArticle(ArticleRequest request) {
        Article article = articleMapper.toEntity(request);
        Article saved = articleRepo.save(article);
        return articleMapper.toResponse(saved);
    }

    @Override
    public List<ArticleResponse> saveArticles(List<ArticleRequest> requests) {
        List<Article> articles = requests.stream()
                .map(articleMapper::toEntity)
                .collect(Collectors.toList());
        List<Article> saved = articleRepo.saveAll(articles);
        return saved.stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleResponse selectArticleById(long id) {
        return articleRepo.findById(id)
                .map(articleMapper::toResponse)
                .orElseGet(() -> ArticleResponse.builder()
                        .idArticle(null)
                        .nomArticle("default nom")
                        .prixArticle(0.0)
                        .typeArticle("SNACK")  // String si votre DTO l'exige ; sinon TypeArticle.SNACK
                        .build());
    }

    @Override
    public List<ArticleResponse> selectAllArticles() {
        return articleRepo.findAll().stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteArticle(long id) {
        articleRepo.deleteById(id);
    }

    @Override
    public void deleteAllArticles() {
        articleRepo.deleteAll();
    }

    @Override
    public long countingArticles() {
        return articleRepo.count();
    }

    @Override
    public boolean verifArticleById(long id) {
        return articleRepo.existsById(id);
    }

    // Nouvelles implémentations pour ArticleRepository
    @Override
    public List<ArticleResponse> getArticlesByNomArticle(String nomArticle) {
        return articleRepo.findByNomArticle(nomArticle).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> getArticlesByPrixArticle(Double prixArticle) {
        return articleRepo.findByPrixArticle(prixArticle).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public long countArticlesByNomArticle(String nomArticle) {
        return articleRepo.countByNomArticle(nomArticle);
    }

    @Override
    public void deleteArticlesByNomArticle(String nomArticle) {
        articleRepo.deleteByNomArticle(nomArticle);
    }

    @Override
    public List<ArticleResponse> getArticlesByNomArticleAndPrixArticle(String nomArticle, Double prixArticle) {
        return articleRepo.findByNomArticleAndPrixArticle(nomArticle, prixArticle).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> getArticlesByTypeArticleAndNomArticleContainingIgnoreCase(TypeArticle typeArticle, String mot) {
        return articleRepo.findByTypeArticleAndNomArticleContainingIgnoreCase(typeArticle, mot).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> getArticlesByTypeArticlesIn(List<TypeArticle> types) {
        return articleRepo.findByTypeArticleIn(types).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> getArticlesByPrixArticleBetween(Double min, Double max) {
        return articleRepo.findByPrixArticleBetween(min, max).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> getArticlesByPrixArticleGreaterThan(Double prixArticle) {
        return articleRepo.findByPrixArticleGreaterThan(prixArticle).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> getArticlesByPrixArticleGreaterThanEqual(Double prixArticle) {
        return articleRepo.findByPrixArticleGreaterThanEqual(prixArticle).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> getArticlesByPrixArticleLessThan(Double prixArticle) {
        return articleRepo.findByPrixArticleLessThan(prixArticle).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> getArticlesByPrixArticleLessThanEqual(Double prixArticle) {
        return articleRepo.findByPrixArticleLessThanEqual(prixArticle).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> getArticlesByNomArticleStartingWithAndTypeArticleOrderByPrixArticle(String nomStart, TypeArticle typeArticle) {
        return articleRepo.findByNomArticleStartingWithAndTypeArticleOrderByPrixArticle(nomStart, typeArticle).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> getArticlesByNomArticleStartingWith(String nomStart) {
        return articleRepo.findByNomArticleStartingWith(nomStart).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> getArticlesByNomArticleEndingWith(String nomEnd) {
        return articleRepo.findByNomArticleEndingWith(nomEnd).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> getArticlesByNomArticleIsNull() {
        return articleRepo.findByNomArticleIsNull().stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> getArticlesByTypeArticleIsNotNull() {
        return articleRepo.findByTypeArticleIsNotNull().stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }



     @Override
     @Transactional
     public ArticleResponse addArticleWithCascade(ArticleRequest request) {
         Article article = articleMapper.toEntity(request);

         // Auto-créer Promotion si liste vide
         if (article.getPromotions() == null || article.getPromotions().isEmpty()) {
             Promotion promo = Promotion.builder()
                     .poucentagePromotion("10%")  // Valeur par défaut
                     .dateDebutPromotion(LocalDate.now())
                     .build();

             // FIX : Initialiser la liste articles si null (bidirectionnel sûr)
             if (promo.getArticles() == null) {
                 promo.setArticles(new ArrayList<>());  // Init avec ArrayList vide
             }

             if (article.getPromotions() == null) {
                 article.setPromotions(new ArrayList<>());  // Init liste Article si null
             }
             article.getPromotions().add(promo);
             promo.getArticles().add(article);  // Maintenant sûr : liste non null
         }

         Article saved = articleRepo.save(article);  // Cascade persiste/ajoute Promotions
         return articleMapper.toResponse(saved);
     }


     // AJOUT : Méthode 2 - Suppression en Cascade
     @Override
     @Transactional
     public void deleteArticleWithCascade(Long id) {
         Optional<Article> articleOpt = articleRepo.findByIdWithPromotions(id);  // Fetch avec Promotions
         if (articleOpt.isPresent()) {
             Article article = articleOpt.get();
             // Unset manuel pour trigger orphanRemoval sur Promotions
             if (article.getPromotions() != null && !article.getPromotions().isEmpty()) {
                 for (Promotion promo : new ArrayList<>(article.getPromotions())) {  // Copie pour éviter ConcurrentModif
                     promo.getArticles().remove(article);  // Unset côté Promotion
                 }
                 article.getPromotions().clear();  // Clear pour cascade
             }
             articleRepo.delete(article);  // Cascade supprime Promotions orphelines
         }
     }




     // AJOUT : Impl de la query repo (pour complétude)
     @Override
     public Optional<Article> findArticleByIdWithPromotions(Long id) {
         return articleRepo.findByIdWithPromotions(id);
     }

     public List<ArticleResponse> findPromotedArticlesForMonth(YearMonth month) {
         LocalDate startOfMonth = month.atDay(1);
         LocalDate endOfMonth = month.atEndOfMonth();
         List<Article> promotedArticles = articleRepo.findPromotedArticlesForMonth(startOfMonth, endOfMonth);
         return promotedArticles.stream()
                 .map(articleMapper::toResponse)
                 .collect(Collectors.toList());
     }


}