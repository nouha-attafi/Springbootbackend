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
import tn.esprit._4twin6.dto.CarteFideliteDTO.CarteFideliteRequest;
import tn.esprit._4twin6.dto.CarteFideliteDTO.CarteFideliteResponse;
import tn.esprit._4twin6.services.cartefidelite.ICartefideliteServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Tag(name = "Loyalty Card Management", description = "Manage customer loyalty cards and points")
@RequestMapping("cartesfidelite")
public class CartefideliteRestController {

    private final ICartefideliteServiceImpl carteFideliteService;

    // CRUD Endpoints
    @GetMapping
    @Operation(summary = "Get all loyalty cards")
    public List<CarteFideliteResponse> selectAllCartesFidelite() {
        return carteFideliteService.selectAllCartesFidelite();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get loyalty card by ID")
    public ResponseEntity<CarteFideliteResponse> selectCarteFideliteById(@PathVariable long id) {
        CarteFideliteResponse response = carteFideliteService.selectCarteFideliteById(id);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Add new loyalty card")
    public CarteFideliteResponse addCarteFidelite(@RequestBody CarteFideliteRequest request) {
        return carteFideliteService.addCarteFidelite(request);
    }

    @PostMapping("/batch")
    @Operation(summary = "Add multiple loyalty cards")
    public List<CarteFideliteResponse> saveCartesFidelite(@RequestBody List<CarteFideliteRequest> requests) {
        return carteFideliteService.saveCartesFidelite(requests);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete loyalty card by ID")
    public void deleteCarteFidelite(@PathVariable long id) {
        carteFideliteService.deleteCarteFidelite(id);
    }

    @DeleteMapping
    @Operation(summary = "Delete all loyalty cards")
    public void deleteAllCartesFidelite() {
        carteFideliteService.deleteAllCartesFidelite();
    }

    @GetMapping("/count")
    @Operation(summary = "Count all loyalty cards")
    public long countCartesFidelite() {
        return carteFideliteService.countingCartesFidelite();
    }

    @GetMapping("/exists/{id}")
    @Operation(summary = "Check if loyalty card exists by ID")
    public boolean verifCarteFideliteById(@PathVariable long id) {
        return carteFideliteService.verifCarteFideliteById(id);
    }

    // =============================
    //        QUERY ENDPOINTS
    // =============================

    // 1. Trouver les cartes avec un nombre exact de points
    @GetMapping("/search/points-exact/{pointsAcumules}")
    @Operation(summary = "Find loyalty cards by exact points")
    public List<CarteFideliteResponse> findByPointsAcumules(@PathVariable Integer pointsAcumules) {
        return carteFideliteService.findCartesByPointsAcumules(pointsAcumules);
    }

    // 2. Trouver les cartes créées à une date spécifique
    @GetMapping("/search/date-creation/{dateCreation}")
    @Operation(summary = "Find loyalty cards by creation date")
    public List<CarteFideliteResponse> findByDateCreation(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateCreation) {
        return carteFideliteService.findCartesByDateCreation(dateCreation);
    }

    // 3. Compter les cartes avec plus de X points
    @GetMapping("/count/points-greater/{pointsAcumules}")
    @Operation(summary = "Count loyalty cards with points > given")
    public long countByPointsAcumulesGreaterThan(@PathVariable Integer pointsAcumules) {
        return carteFideliteService.countCartesByPointsAcumulesGreaterThan(pointsAcumules);
    }

    // 4. Supprimer les cartes créées avant une date
    @DeleteMapping("/delete/before-date/{date}")
    @Operation(summary = "Delete loyalty cards created before date")
    public void deleteByDateCreationBefore(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        carteFideliteService.deleteCartesByDateCreationBefore(date);
    }

    // 5. Trouver les cartes avec des points dans une plage, créées après une date
    @GetMapping("/search/points-range-after-date/{minPoints}/{maxPoints}/{dateCreation}")
    @Operation(summary = "Find loyalty cards by points range and after creation date")
    public List<CarteFideliteResponse> findByPointsRangeAndAfterDate(
            @PathVariable Integer minPoints,
            @PathVariable Integer maxPoints,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateCreation) {
        return carteFideliteService.findCartesByPointsAcumulesBetweenAndDateCreationAfter(minPoints, maxPoints, dateCreation);
    }

    // 6. Trouver les cartes avec au moins X points, triées par date de création
    @GetMapping("/search/points-min-sorted-creation/{pointsAcumules}")
    @Operation(summary = "Find loyalty cards with min points, sorted by creation date")
    public List<CarteFideliteResponse> findByMinPointsSortedCreation(@PathVariable Integer pointsAcumules) {
        return carteFideliteService.findCartesByPointsAcumulesGreaterThanEqualOrderByDateCreationAsc(pointsAcumules);
    }

    // 7. Trouver les cartes créées entre deux dates
    @GetMapping("/search/date-range/{startDate}/{endDate}")
    @Operation(summary = "Find loyalty cards by creation date range")
    public List<CarteFideliteResponse> findByDateRange(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return carteFideliteService.findCartesByDateCreationBetween(startDate, endDate);
    }

    // 8. Trouver les cartes avec peu de points OU créées avant une date
    @GetMapping("/search/low-points-or-before-date/{maxPoints}/{date}")
    @Operation(summary = "Find loyalty cards with low points OR before date")
    public List<CarteFideliteResponse> findLowPointsOrBeforeDate(
            @PathVariable Integer maxPoints,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return carteFideliteService.findCartesByPointsAcumulesLessThanOrDateCreationBefore(maxPoints, date);
    }

    // 9. Trouver la carte avec le plus de points
    @GetMapping("/search/top-points")
    @Operation(summary = "Find loyalty card with max points")
    public ResponseEntity<CarteFideliteResponse> findTopByPoints() {
        Optional<CarteFideliteResponse> response = carteFideliteService.findTopCarteByPointsAcumulesDesc();
        return response.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // 10. Trouver les cartes sans date de création
    @GetMapping("/search/no-date-creation")
    @Operation(summary = "Find loyalty cards without creation date")
    public List<CarteFideliteResponse> findNoDateCreation() {
        return carteFideliteService.findCartesByDateCreationIsNull();
    }

    // 11. Trouver les cartes avec des points accumulés renseignés
    @GetMapping("/search/with-points")
    @Operation(summary = "Find loyalty cards with points specified")
    public List<CarteFideliteResponse> findWithPoints() {
        return carteFideliteService.findCartesByPointsAcumulesIsNotNull();
    }

    // 12. Trouver les cartes avec leur client propriétaire (par nom et prénom)
    @GetMapping("/search/client/{nom}/{prenom}")
    @Operation(summary = "Find loyalty cards by client name and first name")
    public List<CarteFideliteResponse> findByClientNomAndPrenom(
            @PathVariable String nom,
            @PathVariable String prenom) {
        return carteFideliteService.findCartesByClientNomAndPrenom(nom, prenom);
    }

    // 13. Trouver top 5 des cartes avec le plus de points
    @GetMapping("/search/top-5-points")
    @Operation(summary = "Find top 5 loyalty cards by points")
    public List<CarteFideliteResponse> findTop5ByPoints() {
        return carteFideliteService.findFirst5CartesByOrderByPointsAcumulesDesc();
    }
}