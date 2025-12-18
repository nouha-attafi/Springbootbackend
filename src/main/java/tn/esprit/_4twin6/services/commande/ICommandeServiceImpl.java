package tn.esprit._4twin6.services.commande;

import tn.esprit._4twin6.dto.CommandeDTO.CommandeRequest;
import tn.esprit._4twin6.dto.CommandeDTO.CommandeResponse;
import tn.esprit._4twin6.entities.Commande;
import tn.esprit._4twin6.enums.StatusCommande;

import java.time.LocalDate;
import java.util.List;

public interface ICommandeServiceImpl {
    // ✅ Add one commande (from DTO)
    CommandeResponse addCommande(CommandeRequest request);

    // ✅ Add multiple commandes
    List<CommandeResponse> saveCommandes(List<CommandeRequest> requests);

    // ✅ Get one commande by ID
    CommandeResponse selectCommandeById(long id);

    // ✅ Get all commandes
    List<CommandeResponse> selectAllCommandes();

    // ✅ Delete one commande by ID
    void deleteCommandeById(long id);

    // ✅ Delete all commandes
    void deleteAllCommandes();

    // ✅ Count total commandes
    long countingCommandes();

    // ✅ Verify if a commande exists
    boolean verifCommandeById(long id);

    // =============================
    //        JPQL QUERY METHODS
    // =============================

    // 1. Trouver toutes les commandes par date exacte
    List<CommandeResponse> getCommandesByDateCommande(LocalDate date);

    // 2. Trouver les commandes par total exact
    List<CommandeResponse> getCommandesByTotalCommande(float total);

    // 3. Compter le nombre de commandes pour une date spécifique
    long countCommandesByDateCommande(LocalDate date);

    // 4. Supprimer toutes les commandes pour une date spécifique
    void deleteCommandesByDateCommande(LocalDate date);

    // 5. Trouver les commandes pour une date avec un total spécifique
    List<CommandeResponse> getCommandesByDateCommandeAndTotalCommande(LocalDate date, float total);

    // 6. Trouver les commandes pour un status et nom client contenant un mot (insensible à la casse)
    List<CommandeResponse> getCommandesByStatusCommandeAndClientNomContainingIgnoreCase(StatusCommande status, String mot);

    // 7. Trouver les commandes pour une liste de status
    List<CommandeResponse> getCommandesByStatusCommandeIn(List<StatusCommande> statuses);

    // 8. Trouver les commandes avec un total dans une plage spécifique
    List<CommandeResponse> getCommandesByTotalCommandeBetween(float min, float max);

    // 9. Trouver les commandes avec un total supérieur au total passé en paramètre
    List<CommandeResponse> getCommandesByTotalCommandeGreaterThan(float total);

    // 10. Trouver les commandes avec un total supérieur ou égal au total passé en paramètre
    List<CommandeResponse> getCommandesByTotalCommandeGreaterThanEqual(float total);

    // 11. Trouver les commandes avec un total inférieur au total passé en paramètre
    List<CommandeResponse> getCommandesByTotalCommandeLessThan(float total);

    // 12. Trouver les commandes avec un total inférieur ou égal au total passé en paramètre
    List<CommandeResponse> getCommandesByTotalCommandeLessThanEqual(float total);

    // 13. Trouver les commandes dont le nom client commence par, pour un status, triées par total
    List<CommandeResponse> getCommandesByClientNomStartingWithAndStatusCommandeOrderByTotalCommande(String nomStart, StatusCommande status);

    // 14. Trouver les commandes dont le prénom client commence par une chaîne spécifique
    List<CommandeResponse> getCommandesByClientPrenomStartingWith(String prenomStart);

    // 15. Trouver les commandes dont le nom client se termine par une terminaison spécifique
    List<CommandeResponse> getCommandesByClientNomEndingWith(String nomEnd);

    // 16. Trouver les commandes où la date est null
    List<CommandeResponse> getCommandesByDateCommandeIsNull();

    // 17. Trouver les commandes où le status n'est pas null
    List<CommandeResponse> getCommandesByStatusCommandeIsNotNull();

}