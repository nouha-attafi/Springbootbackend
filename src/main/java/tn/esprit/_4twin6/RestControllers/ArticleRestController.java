package tn.esprit._4twin6.RestControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit._4twin6.dto.ArticleDTO.ArticleRequest;
import tn.esprit._4twin6.dto.ArticleDTO.ArticleResponse;
import tn.esprit._4twin6.enums.TypeArticle;
import tn.esprit._4twin6.services.article.IArticleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Tag(name = "Article Management", description = "Manage cafe menu items and products")
@RequestMapping("articles")
public class ArticleRestController {

    private final IArticleService articleService;

    // Endpoints CRUD existants (inchangés)
    @GetMapping
    @Operation(summary = "Get all articles")
    public List<ArticleResponse> selectAllArticles() {
        return articleService.selectAllArticles();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get article by ID")
    public ArticleResponse selectArticleById(@PathVariable long id) {
        return articleService.selectArticleById(id);
    }

    @PostMapping
    @Operation(summary = "Add new article")
    public ArticleResponse addArticle(@RequestBody ArticleRequest request) {
        return articleService.addArticle(request);
    }

    @PostMapping("/batch")
    @Operation(summary = "Add multiple articles")
    public List<ArticleResponse> saveArticles(@RequestBody List<ArticleRequest> requests) {
        return articleService.saveArticles(requests);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete article by ID")
    public void deleteArticleById(@PathVariable long id) {
        articleService.deleteArticle(id);
    }

    @DeleteMapping
    @Operation(summary = "Delete all articles")
    public void deleteAllArticles() {
        articleService.deleteAllArticles();
    }

    @GetMapping("/count")
    @Operation(summary = "Count all articles")
    public long countArticles() {
        return articleService.countingArticles();
    }

    @GetMapping("/exists/{id}")
    @Operation(summary = "Check if article exists by ID")
    public boolean verifArticleById(@PathVariable long id) {
        return articleService.verifArticleById(id);
    }

    // Nouveaux endpoints pour les méthodes du repository
    // 1. Trouver tous les articles d'un nom spécifique
    @GetMapping("/byNomArticle/{nomArticle}")
    @Operation(summary = "Get articles by name")
    public List<ArticleResponse> getArticlesByNomArticle(@PathVariable String nomArticle) {
        return articleService.getArticlesByNomArticle(nomArticle);
    }

    // 2. Trouver les articles par prix exact
    @GetMapping("/byPrixArticle/{prixArticle}")
    @Operation(summary = "Get articles by exact price")
    public List<ArticleResponse> getArticlesByPrixArticle(@PathVariable Double prixArticle) {
        return articleService.getArticlesByPrixArticle(prixArticle);
    }

    // 3. Compter le nombre d'articles avec un nom spécifique
    @GetMapping("/countByNomArticle/{nomArticle}")
    @Operation(summary = "Count articles by name")
    public long countArticlesByNomArticle(@PathVariable String nomArticle) {
        return articleService.countArticlesByNomArticle(nomArticle);
    }

    // 4. Supprimer tous les articles d'un nom spécifique
    @DeleteMapping("/byNomArticle/{nomArticle}")
    @Operation(summary = "Delete all articles by name")
    public void deleteArticlesByNomArticle(@PathVariable String nomArticle) {
        articleService.deleteArticlesByNomArticle(nomArticle);
    }

    // 5. Trouver les articles d'un nom avec un prix spécifique
    @GetMapping("/byNomArticleAndPrixArticle/{nomArticle}/{prixArticle}")
    @Operation(summary = "Get articles by name and price")
    public List<ArticleResponse> getArticlesByNomArticleAndPrixArticle(
            @PathVariable String nomArticle,
            @PathVariable Double prixArticle) {
        return articleService.getArticlesByNomArticleAndPrixArticle(nomArticle, prixArticle);
    }

    // 6. Trouver les articles dont le nom contient un mot, insensible à la casse du nom (type exact)
    @GetMapping("/byTypeArticleIgnoreCaseAndNomArticleContaining/{typeArticle}/{mot}")
    @Operation(summary = "Get articles by type (exact) and name containing word (ignore case)")
    public List<ArticleResponse> getArticlesByTypeArticleAndNomArticleContainingIgnoreCase(
            @PathVariable String typeArticle,
            @PathVariable String mot) {
        TypeArticle enumType = TypeArticle.valueOf(typeArticle.toUpperCase()); // Conversion String -> Enum
        return articleService.getArticlesByTypeArticleAndNomArticleContainingIgnoreCase(enumType, mot);
    }

    // 7. Trouver les articles situés dans une liste de types
    @GetMapping("/byTypeArticlesIn")
    @Operation(summary = "Get articles by list of types")
    public List<ArticleResponse> getArticlesByTypeArticlesIn(
            @RequestParam List<String> types) {  // String pour flexibilité, convert in controller
        List<TypeArticle> enumTypes = types.stream()
                .map(t -> TypeArticle.valueOf(t.toUpperCase()))
                .collect(Collectors.toList());
        return articleService.getArticlesByTypeArticlesIn(enumTypes);
    }

    // 8. Trouver les articles avec un prix dans une plage spécifique
    @GetMapping("/byPrixArticleBetween/{min}/{max}")
    @Operation(summary = "Get articles by price range")
    public List<ArticleResponse> getArticlesByPrixArticleBetween(
            @PathVariable Double min,
            @PathVariable Double max) {
        return articleService.getArticlesByPrixArticleBetween(min, max);
    }

    // 9. Trouver les articles avec un prix supérieur au prix passé en paramètre
    @GetMapping("/byPrixArticleGreaterThan/{prixArticle}")
    @Operation(summary = "Get articles with price > given")
    public List<ArticleResponse> getArticlesByPrixArticleGreaterThan(@PathVariable Double prixArticle) {
        return articleService.getArticlesByPrixArticleGreaterThan(prixArticle);
    }

    // 10. Trouver les articles avec un prix supérieur ou égal au prix passé en paramètre
    @GetMapping("/byPrixArticleGreaterThanEqual/{prixArticle}")
    @Operation(summary = "Get articles with price >= given")
    public List<ArticleResponse> getArticlesByPrixArticleGreaterThanEqual(@PathVariable Double prixArticle) {
        return articleService.getArticlesByPrixArticleGreaterThanEqual(prixArticle);
    }

    // 11. Trouver les articles avec un prix inférieur au prix passé en paramètre
    @GetMapping("/byPrixArticleLessThan/{prixArticle}")
    @Operation(summary = "Get articles with price < given")
    public List<ArticleResponse> getArticlesByPrixArticleLessThan(@PathVariable Double prixArticle) {
        return articleService.getArticlesByPrixArticleLessThan(prixArticle);
    }

    // 12. Trouver les articles avec un prix inférieur ou égal au prix passé en paramètre
    @GetMapping("/byPrixArticleLessThanEqual/{prixArticle}")
    @Operation(summary = "Get articles with price <= given")
    public List<ArticleResponse> getArticlesByPrixArticleLessThanEqual(@PathVariable Double prixArticle) {
        return articleService.getArticlesByPrixArticleLessThanEqual(prixArticle);
    }

    // 13. Trouver les articles dont le nom commence par, dans un type, triés par prix
    @GetMapping("/byNomArticleStartingWithAndTypeArticleOrderByPrixArticle/{nomStart}/{typeArticle}")
    @Operation(summary = "Get articles by name start, type, ordered by price")
    public List<ArticleResponse> getArticlesByNomArticleStartingWithAndTypeArticleOrderByPrixArticle(
            @PathVariable String nomStart,
            @PathVariable String typeArticle) {
        TypeArticle enumType = TypeArticle.valueOf(typeArticle.toUpperCase());
        return articleService.getArticlesByNomArticleStartingWithAndTypeArticleOrderByPrixArticle(nomStart, enumType);
    }

    // 14. Trouver les articles dont le nom commence par une chaîne spécifique
    @GetMapping("/byNomArticleStartingWith/{nomStart}")
    @Operation(summary = "Get articles by name starting with")
    public List<ArticleResponse> getArticlesByNomArticleStartingWith(@PathVariable String nomStart) {
        return articleService.getArticlesByNomArticleStartingWith(nomStart);
    }

    // 15. Trouver les articles dont le nom se termine par une terminaison spécifique
    @GetMapping("/byNomArticleEndingWith/{nomEnd}")
    @Operation(summary = "Get articles by name ending with")
    public List<ArticleResponse> getArticlesByNomArticleEndingWith(@PathVariable String nomEnd) {
        return articleService.getArticlesByNomArticleEndingWith(nomEnd);
    }

    // 16. Trouver les articles où le champ nom est null
    @GetMapping("/byNomArticleIsNull")
    @Operation(summary = "Get articles where name is null")
    public List<ArticleResponse> getArticlesByNomArticleIsNull() {
        return articleService.getArticlesByNomArticleIsNull();
    }

    // 17. Trouver les articles où le type n'est pas null
    @GetMapping("/byTypeArticleIsNotNull")
    @Operation(summary = "Get articles where type is not null")
    public List<ArticleResponse> getArticlesByTypeArticleIsNotNull() {
        return articleService.getArticlesByTypeArticleIsNotNull();
    }



    // AJOUT : Endpoint 1 - Ajout en Cascade
    @PostMapping("/add-cascade")
    @Operation(summary = "Ajouter Article avec Cascade Promotions")
    public ArticleResponse addWithCascade(@RequestBody ArticleRequest request) {
        return articleService.addArticleWithCascade(request);
    }

    // AJOUT : Endpoint 2 - Suppression en Cascade
    @DeleteMapping("/delete-cascade/{id}")
    @Operation(summary = "Supprimer Article avec Cascade Promotions")
    public ResponseEntity<String> deleteWithCascade(@PathVariable Long id) {
        articleService.deleteArticleWithCascade(id);
        return ResponseEntity.ok("Article et Promotions supprimés avec cascade.");
    }
}