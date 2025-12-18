package tn.esprit._4twin6.RestControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit._4twin6.dto.PromotionDTO.PromotionRequest;
import tn.esprit._4twin6.dto.PromotionDTO.PromotionResponse;
import tn.esprit._4twin6.entities.Promotion;
import tn.esprit._4twin6.services.promotion.IPromotionServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Promotion Management", description = "Manage discounts and special offers")
@RequestMapping("promotions")
public class PromotionRestController {

    private final IPromotionServiceImpl promotionService;

    // CRUD Endpoints
    @PostMapping
    @Operation(summary = "Add new promotion")
    public PromotionResponse addPromotion(@RequestBody PromotionRequest request) {
        return promotionService.addPromotion(request);
    }

    @PostMapping("/batch")
    @Operation(summary = "Add multiple promotions")
    public List<PromotionResponse> savePromotions(@RequestBody List<PromotionRequest> requests) {
        return promotionService.savePromotions(requests);
    }

    @GetMapping
    @Operation(summary = "Get all promotions")
    public List<PromotionResponse> selectAllPromotions() {
        return promotionService.selectAllPromotions();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get promotion by ID")
    public ResponseEntity<PromotionResponse> selectPromotionById(@PathVariable long id) {
        PromotionResponse promo = promotionService.selectPromotionById(id);
        return promo != null ? ResponseEntity.ok(promo) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete promotion by ID")
    public void deletePromotionById(@PathVariable long id) {
        promotionService.deletePromotionById(id);
    }

    @DeleteMapping
    @Operation(summary = "Delete all promotions")
    public void deleteAllPromotions() {
        promotionService.deleteAllPromotions();
    }

    @GetMapping("/count")
    @Operation(summary = "Count all promotions")
    public long countPromotions() {
        return promotionService.countingPromotions();
    }

    @GetMapping("/exists/{id}")
    @Operation(summary = "Check if promotion exists by ID")
    public boolean verifPromotionById(@PathVariable long id) {
        return promotionService.verifPromotionById(id);
    }

    // =============================
    //        QUERY ENDPOINTS
    // =============================

    // 1. Trouver les promotions par pourcentage exact
    @GetMapping("/search/pourcentage-exact")
    @Operation(summary = "Find promotions by exact percentage")
    public List<PromotionResponse> findByPoucentageExact(@RequestParam String poucentage) {
        return promotionService.findPromotionsByPoucentagePromotion(poucentage);
    }

    // 2. Trouver les promotions par date de début
    @GetMapping("/search/date-debut")
    @Operation(summary = "Find promotions by start date")
    public List<PromotionResponse> findByDateDebut(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut) {
        return promotionService.findPromotionsByDateDebutPromotion(dateDebut);
    }

    // 3. Trouver les promotions par date de fin
    @GetMapping("/search/date-fin")
    @Operation(summary = "Find promotions by end date")
    public List<PromotionResponse> findByDateFin(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
        return promotionService.findPromotionsByDateFin(dateFin);
    }

    // 4. Vérifier l'existence d'une promotion par pourcentage
    @GetMapping("/exists/pourcentage")
    @Operation(summary = "Check existence by percentage")
    public boolean existsByPoucentage(@RequestParam String poucentage) {
        return promotionService.existsPromotionByPoucentagePromotion(poucentage);
    }

    // 5. Compter les promotions débutant après une date
    @GetMapping("/count/debut-after")
    @Operation(summary = "Count promotions starting after date")
    public long countDebutAfter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return promotionService.countPromotionsByDateDebutPromotionAfter(date);
    }

    // 6. Trouver les promotions actives à une date donnée
    @GetMapping("/search/active-at-date")
    @Operation(summary = "Find active promotions at given date")
    public List<PromotionResponse> findActiveAtDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return promotionService.findActivePromotionsAtDate(date);
    }

    // 7. Trouver les promotions avec un pourcentage spécifique débutant dans une période
    @GetMapping("/search/pourcentage-debut-period")
    @Operation(summary = "Find promotions by percentage starting in period")
    public List<PromotionResponse> findByPourcentageAndDebutPeriod(
            @RequestParam String poucentage,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return promotionService.findPromotionsByPoucentagePromotionAndDateDebutPromotionBetween(poucentage, start, end);
    }

    // 8. Trouver les promotions valides à une date spécifique
    @GetMapping("/search/valid-at-date")
    @Operation(summary = "Find valid promotions at specific date")
    public List<PromotionResponse> findValidAtDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return promotionService.findValidPromotionsAtDate(date);
    }

    // 9. Trouver les promotions avec certains pourcentages, triées par date de début
    @GetMapping("/search/pourcentages-sorted-debut")
    @Operation(summary = "Find promotions by percentages, sorted by start date")
    public List<PromotionResponse> findByPourcentagesSortedDebut(@RequestParam List<String> pourcentages) {
        return promotionService.findPromotionsByPoucentagePromotionInOrderByDateDebutPromotionAsc(pourcentages);
    }

    // 10. Trouver les promotions actives triées par pourcentage
    @GetMapping("/search/active-sorted-pourcentage")
    @Operation(summary = "Find active promotions sorted by percentage")
    public List<PromotionResponse> findActiveSortedPourcentage() {
        return promotionService.findActivePromotionsOrderByPoucentagePromotionAsc();
    }

    // 11. Trouver les promotions sans date de fin
    @GetMapping("/search/no-date-fin")
    @Operation(summary = "Find promotions without end date")
    public List<PromotionResponse> findNoDateFin() {
        return promotionService.findPromotionsByDateFinIsNull();
    }

    // 12. Trouver les promotions avec un pourcentage renseigné
    @GetMapping("/search/with-pourcentage")
    @Operation(summary = "Find promotions with specified percentage")
    public List<PromotionResponse> findWithPourcentage() {
        return promotionService.findPromotionsByPoucentagePromotionIsNotNull();
    }

    // 13. Trouver les promotions avec leurs articles associés
    @GetMapping("/search/with-articles")
    @Operation(summary = "Find promotions with associated articles")
    public List<PromotionResponse> findWithArticles() {
        return promotionService.findAllPromotionsWithArticles();
    }

    // 14. Trouver les promotions expirées
    @GetMapping("/search/expired")
    @Operation(summary = "Find expired promotions")
    public List<PromotionResponse> findExpired() {
        return promotionService.findExpiredPromotions();
    }

    @Operation(summary = "Assign Promotion to an Article")
    @PostMapping("/affect/{idPromo}/to/{idArticle}")
    public void affecterPromotionAArticle(@PathVariable long idArticle, @PathVariable long idPromo) {
        promotionService.affecterPromotionAArticle(idArticle, idPromo);
    }

    @Operation(summary = "Remove Promotion from an Article")
    @PostMapping("/desaffect/{idPromo}/from/{idArticle}")
    public void  desaffecterPromotionAArticle(@PathVariable long idArticle, @PathVariable long idPromo) {
        promotionService. desaffecterPromotionAArticle(idArticle, idPromo);
    }

    // Nouvelle méthode pour ajouter une promotion et l'affecter à un article
    @Operation(summary = "Add Promotion and Assign to Article")
    @PostMapping("/add-and-assign/{idArticle}")
    public void ajouterPromotionEtAffecterAArticle(@RequestBody Promotion p, @PathVariable Long idArticle) {
        promotionService.ajouterPromotionEtAffecterAArticle(p, idArticle);
    }

   }