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
import org.springframework.web.bind.annotation.*;
import tn.esprit._4twin6.dto.CommandeDTO.CommandeRequest;
import tn.esprit._4twin6.dto.CommandeDTO.CommandeResponse;
import tn.esprit._4twin6.entities.Commande;
import tn.esprit._4twin6.enums.StatusCommande;
import tn.esprit._4twin6.services.commande.ICommandeServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Order Management", description = "Manage customer orders and status")
@RequestMapping("commandes")
public class CommandeRestController {

    private ICommandeServiceImpl commandeService;

    // ✅ Get all commandes
    @GetMapping
    @Operation(summary = "Get all orders")
    public List<CommandeResponse> selectAllCommandes() {
        return commandeService.selectAllCommandes();
    }

    // ✅ Get commande by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID")
    public CommandeResponse selectCommandeById(@PathVariable long id) {
        return commandeService.selectCommandeById(id);
    }

    // ✅ Add one commande
    @PostMapping
    @Operation(summary = "Add new order")
    public CommandeResponse addCommande(@RequestBody CommandeRequest request) {
        return commandeService.addCommande(request);
    }

    // ✅ Add multiple commandes
    @PostMapping("/batch")
    @Operation(summary = "Add multiple orders")
    public List<CommandeResponse> saveCommandes(@RequestBody List<CommandeRequest> requests) {
        return commandeService.saveCommandes(requests);
    }

    // ✅ Delete commande by ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order by ID")
    public void deleteCommandeById(@PathVariable long id) {
        commandeService.deleteCommandeById(id);
    }

    // ✅ Delete all commandes
    @DeleteMapping
    @Operation(summary = "Delete all orders")
    public void deleteAllCommandes() {
        commandeService.deleteAllCommandes();
    }

    // ✅ Count all commandes
    @GetMapping("/count")
    @Operation(summary = "Count total orders")
    public long countCommandes() {
        return commandeService.countingCommandes();
    }

    // ✅ Check if commande exists by ID
    @GetMapping("/exists/{id}")
    @Operation(summary = "Verify if order exists by ID")
    public boolean verifCommandeById(@PathVariable long id) {
        return commandeService.verifCommandeById(id);
    }

    // =============================
    //        JPQL QUERY ENDPOINTS
    // =============================

    // 1. Trouver toutes les commandes par date exacte
    @GetMapping("/search/date")
    @Operation(summary = "Get orders by exact date")
    public List<CommandeResponse> getCommandesByDateCommande(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return commandeService.getCommandesByDateCommande(date);
    }

    // 2. Trouver les commandes par total exact
    @GetMapping("/search/total")
    @Operation(summary = "Get orders by exact total")
    public List<CommandeResponse> getCommandesByTotalCommande(@RequestParam float total) {
        return commandeService.getCommandesByTotalCommande(total);
    }

    // 3. Compter le nombre de commandes pour une date spécifique
    @GetMapping("/count/date")
    @Operation(summary = "Count orders by date")
    public long countCommandesByDateCommande(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return commandeService.countCommandesByDateCommande(date);
    }

    // 4. Supprimer toutes les commandes pour une date spécifique
    @DeleteMapping("/delete/date")
    @Operation(summary = "Delete orders by date")
    public void deleteCommandesByDateCommande(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        commandeService.deleteCommandesByDateCommande(date);
    }

    // 5. Trouver les commandes pour une date avec un total spécifique
    @GetMapping("/search/date-total")
    @Operation(summary = "Get orders by date and total")
    public List<CommandeResponse> getCommandesByDateCommandeAndTotalCommande(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam float total) {
        return commandeService.getCommandesByDateCommandeAndTotalCommande(date, total);
    }

    // 6. Trouver les commandes pour un status et nom client contenant un mot (insensible à la casse)
    @GetMapping("/search/status-client-nom")
    @Operation(summary = "Get orders by status and client name containing")
    public List<CommandeResponse> getCommandesByStatusCommandeAndClientNomContainingIgnoreCase(
            @RequestParam StatusCommande status,
            @RequestParam String mot) {
        return commandeService.getCommandesByStatusCommandeAndClientNomContainingIgnoreCase(status, mot);
    }

    // 7. Trouver les commandes pour une liste de status
    @GetMapping("/search/statuses")
    @Operation(summary = "Get orders by list of statuses")
    public List<CommandeResponse> getCommandesByStatusCommandeIn(@RequestParam List<StatusCommande> statuses) {
        return commandeService.getCommandesByStatusCommandeIn(statuses);
    }

    // 8. Trouver les commandes avec un total dans une plage spécifique
    @GetMapping("/search/total-between")
    @Operation(summary = "Get orders by total between min and max")
    public List<CommandeResponse> getCommandesByTotalCommandeBetween(
            @RequestParam float min,
            @RequestParam float max) {
        return commandeService.getCommandesByTotalCommandeBetween(min, max);
    }

    // 9. Trouver les commandes avec un total supérieur au total passé en paramètre
    @GetMapping("/search/total-greater-than")
    @Operation(summary = "Get orders with total greater than")
    public List<CommandeResponse> getCommandesByTotalCommandeGreaterThan(@RequestParam float total) {
        return commandeService.getCommandesByTotalCommandeGreaterThan(total);
    }

    // 10. Trouver les commandes avec un total supérieur ou égal au total passé en paramètre
    @GetMapping("/search/total-greater-equal")
    @Operation(summary = "Get orders with total greater or equal")
    public List<CommandeResponse> getCommandesByTotalCommandeGreaterThanEqual(@RequestParam float total) {
        return commandeService.getCommandesByTotalCommandeGreaterThanEqual(total);
    }

    // 11. Trouver les commandes avec un total inférieur au total passé en paramètre
    @GetMapping("/search/total-less-than")
    @Operation(summary = "Get orders with total less than")
    public List<CommandeResponse> getCommandesByTotalCommandeLessThan(@RequestParam float total) {
        return commandeService.getCommandesByTotalCommandeLessThan(total);
    }

    // 12. Trouver les commandes avec un total inférieur ou égal au total passé en paramètre
    @GetMapping("/search/total-less-equal")
    @Operation(summary = "Get orders with total less or equal")
    public List<CommandeResponse> getCommandesByTotalCommandeLessThanEqual(@RequestParam float total) {
        return commandeService.getCommandesByTotalCommandeLessThanEqual(total);
    }

    // 13. Trouver les commandes dont le nom client commence par, pour un status, triées par total
    @GetMapping("/search/client-nom-status-total")
    @Operation(summary = "Get orders by client name start, status, ordered by total")
    public List<CommandeResponse> getCommandesByClientNomStartingWithAndStatusCommandeOrderByTotalCommande(
            @RequestParam String nomStart,
            @RequestParam StatusCommande status) {
        return commandeService.getCommandesByClientNomStartingWithAndStatusCommandeOrderByTotalCommande(nomStart, status);
    }

    // 14. Trouver les commandes dont le prénom client commence par une chaîne spécifique
    @GetMapping("/search/client-prenom-start")
    @Operation(summary = "Get orders by client first name starting with")
    public List<CommandeResponse> getCommandesByClientPrenomStartingWith(@RequestParam String prenomStart) {
        return commandeService.getCommandesByClientPrenomStartingWith(prenomStart);
    }

    // 15. Trouver les commandes dont le nom client se termine par une terminaison spécifique
    @GetMapping("/search/client-nom-end")
    @Operation(summary = "Get orders by client name ending with")
    public List<CommandeResponse> getCommandesByClientNomEndingWith(@RequestParam String nomEnd) {
        return commandeService.getCommandesByClientNomEndingWith(nomEnd);
    }

    // 16. Trouver les commandes où la date est null
    @GetMapping("/search/no-date")
    @Operation(summary = "Get orders where date is null")
    public List<CommandeResponse> getCommandesByDateCommandeIsNull() {
        return commandeService.getCommandesByDateCommandeIsNull();
    }

    // 17. Trouver les commandes où le status n'est pas null
    @GetMapping("/search/with-status")
    @Operation(summary = "Get orders where status is not null")
    public List<CommandeResponse> getCommandesByStatusCommandeIsNotNull() {
        return commandeService.getCommandesByStatusCommandeIsNotNull();
    }
}