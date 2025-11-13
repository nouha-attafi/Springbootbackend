package tn.esprit._4twin6.RestControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit._4twin6.dto.ArticleDTO.ArticleRequest;
import tn.esprit._4twin6.dto.ArticleDTO.ArticleResponse;
import tn.esprit._4twin6.entities.Article;
import tn.esprit._4twin6.services.article.IArticleServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Article Management", description = "Manage cafe menu items and products")
@RequestMapping("articles")
public class ArticleRestController {

    private IArticleServiceImpl articleService;
    // ✅ Get all articles
    @GetMapping
    public List<ArticleResponse> selectAllArticles() {
        return articleService.selectAllArticles();
    }

    // ✅ Get article by ID
    @GetMapping("/{id}")
    public ArticleResponse selectArticleById(@PathVariable long id) {
        return articleService.selectArticleById(id);
    }

    // ✅ Add one article
    @PostMapping
    public ArticleResponse addArticle(@RequestBody ArticleRequest request) {
        return articleService.addArticle(request);
    }

    // ✅ Add multiple articles
    @PostMapping("/batch")
    public List<ArticleResponse> saveArticles(@RequestBody List<ArticleRequest> requests) {
        return articleService.saveArticles(requests);
    }

    // ✅ Delete article by ID
    @DeleteMapping("/{id}")
    public void deleteArticleById(@PathVariable long id) {
        articleService.deleteArticle(id);
    }

    // ✅ Delete all articles
    @DeleteMapping
    public void deleteAllArticles() {
        articleService.deleteAllArticles();
    }

    // ✅ Count all articles
    @GetMapping("/count")
    public long countArticles() {
        return articleService.countingArticles();
    }

    // ✅ Check if article exists by ID
    @GetMapping("/exists/{id}")
    public boolean verifArticleById(@PathVariable long id) {
        return articleService.verifArticleById(id);
    }

}