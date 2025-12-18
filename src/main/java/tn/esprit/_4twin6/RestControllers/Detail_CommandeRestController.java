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
import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeRequest;
import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeResponse;
import tn.esprit._4twin6.services.detailcommande.IDetailCommandeServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Order Detail Management", description = "Manage line items in orders")
@RequestMapping("details_commandes")
public class Detail_CommandeRestController {

    private final IDetailCommandeServiceImpl detailService;

    // CRUD Endpoints
    @GetMapping
    public List<DetailCommandeResponse> getAllDetails() {
        return detailService.selectAllDetailsCommande();
    }

    @GetMapping("/{id}")
    public DetailCommandeResponse getDetailById(@PathVariable long id) {
        return detailService.selectDetailCommandeById(id);
    }

    @PostMapping
    public DetailCommandeResponse addDetail(@RequestBody DetailCommandeRequest request) {
        return detailService.addDetailCommande(request);
    }

    @PostMapping("/batch")
    public List<DetailCommandeResponse> addDetailsBatch(@RequestBody List<DetailCommandeRequest> requests) {
        return detailService.saveDetailsCommande(requests);
    }

    @DeleteMapping("/{id}")
    public void deleteDetailById(@PathVariable long id) {
        detailService.deleteDetailCommandeById(id);
    }

    @DeleteMapping
    public void deleteAllDetails() {
        detailService.deleteAllDetailsCommande();
    }

    @GetMapping("/count")
    public long countDetails() {
        return detailService.countingDetailsCommande();
    }

    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable long id) {
        return detailService.verifDetailCommandeById(id);
    }

    // =============================
    //    QUERY ENDPOINTS
    // =============================

    // 1. Trouver les détails par quantité exacte
    @GetMapping("/search/quantite")
    public List<DetailCommandeResponse> findByQuantiteArticle(@RequestParam Integer quantite) {
        return detailService.findDetailsByQuantiteArticle(quantite);
    }

    // 2. Trouver les détails par sous-total exact
    @GetMapping("/search/sous-total")
    public List<DetailCommandeResponse> findBySousTotalDetailArticle(@RequestParam Double sousTotal) {
        return detailService.findDetailsBySousTotalDetailArticle(sousTotal);
    }

    // 3. Compter les détails avec plus de X quantités
    @GetMapping("/count/quantite-greater")
    public long countByQuantiteArticleGreaterThan(@RequestParam Integer quantite) {
        return detailService.countDetailsByQuantiteArticleGreaterThan(quantite);
    }

    // 4. Vérifier l'existence de détails avec un sous-total élevé
    @GetMapping("/exists/sous-total-greater")
    public boolean existsBySousTotalDetailArticleGreaterThan(@RequestParam Double sousTotal) {
        return detailService.existsDetailsBySousTotalDetailArticleGreaterThan(sousTotal);
    }

    // 5. Trouver les détails avec quantité dans plage et sous-total minimum
    @GetMapping("/search/quantite-range-sous-total-min")
    public List<DetailCommandeResponse> findByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThanEqual(
            @RequestParam Integer minQuant,
            @RequestParam Integer maxQuant,
            @RequestParam Double minSousTotal) {
        return detailService.findDetailsByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThanEqual(minQuant, maxQuant, minSousTotal);
    }

    // 6. Trouver les détails avec sous-total dans plage, triés par quantité ASC
    @GetMapping("/search/sous-total-range-sort-quantite")
    public List<DetailCommandeResponse> findBySousTotalDetailArticleBetweenOrderByQuantiteArticleAsc(
            @RequestParam Double min,
            @RequestParam Double max) {
        return detailService.findDetailsBySousTotalDetailArticleBetweenOrderByQuantiteArticleAsc(min, max);
    }

    // 7. Trouver les détails avec sous-total après promo dans plage
    @GetMapping("/search/sous-total-apres-promo-range")
    public List<DetailCommandeResponse> findBySousTotalDetailArticleApresPromoBetween(
            @RequestParam Double min,
            @RequestParam Double max) {
        return detailService.findDetailsBySousTotalDetailArticleApresPromoBetween(min, max);
    }

    // 8. Trouver les détails par quantité ou sous-total minimum
    @GetMapping("/search/quantite-or-sous-total-min")
    public List<DetailCommandeResponse> findByQuantiteArticleOrSousTotalDetailArticleGreaterThan(
            @RequestParam Integer quantite,
            @RequestParam Double minSousTotal) {
        return detailService.findDetailsByQuantiteArticleOrSousTotalDetailArticleGreaterThan(quantite, minSousTotal);
    }

    // 9. Trouver les 5 détails les plus chers
    @GetMapping("/search/top-5-chers")
    public List<DetailCommandeResponse> findFirst5ByOrderBySousTotalDetailArticleDesc() {
        return detailService.findFirst5DetailsByOrderBySousTotalDetailArticleDesc();
    }

    // 10. Trouver les détails sans quantité renseignée
    @GetMapping("/search/no-quantite")
    public List<DetailCommandeResponse> findByQuantiteArticleIsNull() {
        return detailService.findDetailsByQuantiteArticleIsNull();
    }

    // 11. Trouver les détails avec sous-total après promotion renseigné
    @GetMapping("/search/with-sous-total-apres-promo")
    public List<DetailCommandeResponse> findBySousTotalDetailArticleApresPromoIsNotNull() {
        return detailService.findDetailsBySousTotalDetailArticleApresPromoIsNotNull();
    }

    // 12. Trouver les détails avec leur commande et article
    @GetMapping("/search/with-commande-article")
    public List<DetailCommandeResponse> findAllWithCommandeAndArticle() {
        return detailService.findAllDetailsWithCommandeAndArticle();
    }
}