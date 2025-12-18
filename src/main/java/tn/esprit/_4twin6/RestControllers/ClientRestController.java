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
import tn.esprit._4twin6.dto.ClientDTO.ClientRequest;
import tn.esprit._4twin6.dto.ClientDTO.ClientResponse;
import tn.esprit._4twin6.entities.Adresse;
import tn.esprit._4twin6.entities.Client;
import tn.esprit._4twin6.entities.Commande;
import tn.esprit._4twin6.services.client.IClientServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Client Management", description = "Manage cafe customers and their profiles")
@RequestMapping("clients")
public class ClientRestController {

    private final IClientServiceImpl clientService;

    @GetMapping
    public List<ClientResponse> selectAllClients() {
        return clientService.selectAllClients();
    }

    @GetMapping("/{id}")
    public ClientResponse selectClientById(@PathVariable long id) {
        return clientService.selectClientById(id);
    }

    @PostMapping
    public ClientResponse addClient(@RequestBody ClientRequest request) {
        return clientService.addClient(request);
    }

    @PostMapping("/batch")
    public List<ClientResponse> saveClients(@RequestBody List<ClientRequest> requests) {
        return clientService.saveClients(requests);
    }

    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable long id) {
        clientService.deleteClientById(id);
    }

    @DeleteMapping
    public void deleteAllClients() {
        clientService.deleteAllClients();
    }

    @GetMapping("/count")
    public long countClients() {
        return clientService.countingClients();
    }

    @GetMapping("/exists/{id}")
    public boolean verifClientById(@PathVariable long id) {
        return clientService.verifClientById(id);
    }

    // =============================
    //    JPQL QUERY ENDPOINTS
    // =============================

    // 1. Rechercher par nom
    @GetMapping("/search/nom")
    public List<ClientResponse> findByNom(@RequestParam String nom) {
        return clientService.findClientsByNom(nom);
    }

    // 2. Rechercher par prénom
    @GetMapping("/search/prenom")
    public List<ClientResponse> findByPrenom(@RequestParam String prenom) {
        return clientService.findClientsByPrenom(prenom);
    }

    // 3. Rechercher par nom ET prénom
    @GetMapping("/search/nom-prenom")
    public ResponseEntity<ClientResponse> findByNomAndPrenom(
            @RequestParam String nom,
            @RequestParam String prenom) {
        ClientResponse client = clientService.findClientByNomAndPrenom(nom, prenom);
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    // 4. Vérifier si un client existe par nom
    @GetMapping("/exists/nom")
    public boolean existsByNom(@RequestParam String nom) {
        return clientService.existsClientByNom(nom);
    }

    // 5. Compter les clients nés après une date
    @GetMapping("/count/born-after")
    public long countByDateNaissanceAfter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return clientService.countClientsByDateNaissanceAfter(date);
    }

    // 6. Rechercher par nom OU prénom contenant
    @GetMapping("/search/nom-or-prenom-containing")
    public List<ClientResponse> findByNomOrPrenomContaining(@RequestParam String str) {
        return clientService.findClientsByNomOrPrenomContaining(str);
    }

    // 7. Rechercher par nom ET prénom contenant
    @GetMapping("/search/nom-and-prenom-containing")
    public List<ClientResponse> findByNomAndPrenomContaining(@RequestParam String str) {
        return clientService.findClientsByNomAndPrenomContaining(str);
    }

    // 8. Rechercher par date de naissance entre deux dates
    @GetMapping("/search/born-between")
    public List<ClientResponse> findByDateNaissanceBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return clientService.findClientsByDateNaissanceBetween(start, end);
    }

    // 9. Rechercher par nom commençant par ET né avant
    @GetMapping("/search/nom-starts-born-before")
    public List<ClientResponse> findByNomStartingWithAndDateNaissanceBefore(
            @RequestParam String prefix,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return clientService.findClientsByNomStartingWithAndDateNaissanceBefore(prefix, date);
    }

    // 10. Rechercher par ville
    @GetMapping("/search/ville")
    public List<ClientResponse> findByAdresseVille(@RequestParam String ville) {
        return clientService.findClientsByAdresseVille(ville);
    }

    // 11. Rechercher par nom contenant, triés par prénom ASC
    @GetMapping("/search/nom-containing-sort-asc")
    public List<ClientResponse> findByNomContainingOrderByPrenomAsc(@RequestParam String str) {
        return clientService.findClientsByNomContainingOrderByPrenomAsc(str);
    }

    // 12. Rechercher par nom contenant, triés par prénom DESC
    @GetMapping("/search/nom-containing-sort-desc")
    public List<ClientResponse> findByNomContainingOrderByPrenomDesc(@RequestParam String str) {
        return clientService.findClientsByNomContainingOrderByPrenomDesc(str);
    }

    // 13. Rechercher par nom commençant par
    @GetMapping("/search/nom-starts-with")
    public List<ClientResponse> findByNomStartingWith(@RequestParam String letter) {
        return clientService.findClientsByNomStartingWith(letter);
    }

    // 14. Rechercher par prénom se terminant par
    @GetMapping("/search/prenom-ends-with")
    public List<ClientResponse> findByPrenomEndingWith(@RequestParam String suffix) {
        return clientService.findClientsByPrenomEndingWith(suffix);
    }

    // 15. Clients sans date de naissance
    @GetMapping("/search/no-birthdate")
    public List<ClientResponse> findByDateNaissanceIsNull() {
        return clientService.findClientsByDateNaissanceIsNull();
    }

    // 16. Clients avec adresse renseignée
    @GetMapping("/search/with-address")
    public List<ClientResponse> findByAdresseIsNotNull() {
        return clientService.findClientsByAdresseIsNotNull();
    }

    // 17. Rechercher par plusieurs villes
    @GetMapping("/search/villes")
    public List<ClientResponse> findByAdresseVilleIn(@RequestParam List<String> villes) {
        return clientService.findClientsByAdresseVilleIn(villes);
    }

    // 18. Clients dont les points accumulés > valeur
    @GetMapping("/search/points-greater")
    public List<ClientResponse> findBypointsAcumulesGreaterThan(@RequestParam int pts) {
        return clientService.findClientsBypointsAcumulesGreaterThan(pts);
    }

    // 19. Clients dont les points accumulés >= valeur
    @GetMapping("/search/points-greater-equal")
    public List<ClientResponse> findBypointsAcumulesGreaterThanOrEqual(@RequestParam int pts) {
        return clientService.findClientsBypointsAcumulesGreaterThanOrEqual(pts);
    }

    // 20. Clients dont les points accumulés entre deux valeurs
    @GetMapping("/search/points-between")
    public List<ClientResponse> findBypointsAcumulesBetween(
            @RequestParam int min,
            @RequestParam int max) {
        return clientService.findClientsBypointsAcumulesBetween(min, max);
    }

    // 21. Clients ayant commandé un article spécifique
    @GetMapping("/search/by-article")
    public List<ClientResponse> findByCommandeArticleNom(@RequestParam String nomArticle) {
        return clientService.findClientsByCommandeArticleNom(nomArticle);
    }

    // 22. Clients par nom contenant ET type d'article commandé
    @GetMapping("/search/nom-and-article-type")
    public List<ClientResponse> findByNomContainingAndArticleType(
            @RequestParam String nomStr,
            @RequestParam String typeArticle) {
        return clientService.findClientsByNomContainingAndArticleType(nomStr, typeArticle);
    }

    @Operation(summary = "Add Commande and Assign to Client")
    @PostMapping("/add-commande-and-assign/{nomClient}/{prenomClient}")
    public void ajouterCommandeEtAffecterAClient(@RequestBody Commande c, @PathVariable String nomClient, @PathVariable String prenomClient) {
        clientService.ajouterCommandeEtAffecterAClient(c, nomClient, prenomClient);
    }

    // AJOUT : Endpoint 1 - Ajout en Cascade
    @PostMapping("/add-cascade")
    @Operation(summary = "Ajouter Client avec Cascade CarteFidelite")
    public ClientResponse addWithCascade(@RequestBody ClientRequest request) {
        return clientService.addClientWithCascade(request);
    }

    // AJOUT : Endpoint 2 - Suppression en Cascade
    @DeleteMapping("/delete-cascade/{id}")
    @Operation(summary = "Supprimer Client avec Cascade CarteFidelite")
    public ResponseEntity<String> deleteWithCascade(@PathVariable Long id) {
        clientService.deleteClientWithCascade(id);
        return ResponseEntity.ok("Client et CarteFidelite supprimés avec cascade.");
    }


}