package tn.esprit._4twin6.services.client;

import tn.esprit._4twin6.dto.ClientDTO.ClientRequest;
import tn.esprit._4twin6.dto.ClientDTO.ClientResponse;
import tn.esprit._4twin6.entities.Adresse;
import tn.esprit._4twin6.entities.Client;
import tn.esprit._4twin6.entities.Commande;
import tn.esprit._4twin6.enums.StatusCommande;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IClientServiceImpl {
    ClientResponse addClient(ClientRequest request);

    List<ClientResponse> saveClients(List<ClientRequest> requests);

    ClientResponse selectClientById(long id);

    List<ClientResponse> selectAllClients();

    void deleteClientById(long id);

    void deleteAllClients();

    long countingClients();

    boolean verifClientById(long id);

    // =============================
    //        JPQL QUERY METHODS
    // =============================

    List<ClientResponse> findClientsByNom(String nom);

    List<ClientResponse> findClientsByPrenom(String prenom);

    ClientResponse findClientByNomAndPrenom(String nom, String prenom);

    boolean existsClientByNom(String nom);

    long countClientsByDateNaissanceAfter(LocalDate date);

    List<ClientResponse> findClientsByNomOrPrenomContaining(String str);

    List<ClientResponse> findClientsByNomAndPrenomContaining(String str);

    List<ClientResponse> findClientsByDateNaissanceBetween(LocalDate start, LocalDate end);

    List<ClientResponse> findClientsByNomStartingWithAndDateNaissanceBefore(String prefix, LocalDate date);

    List<ClientResponse> findClientsByAdresseVille(String ville);

    List<ClientResponse> findClientsByNomContainingOrderByPrenomAsc(String str);

    List<ClientResponse> findClientsByNomContainingOrderByPrenomDesc(String str);

    List<ClientResponse> findClientsByNomStartingWith(String letter);

    List<ClientResponse> findClientsByPrenomEndingWith(String suffix);

    List<ClientResponse> findClientsByDateNaissanceIsNull();

    List<ClientResponse> findClientsByAdresseIsNotNull();

    List<ClientResponse> findClientsByAdresseVilleIn(List<String> villes);

    // 18. Clients dont les points accumulés > valeur
    List<ClientResponse> findClientsBypointsAcumulesGreaterThan(int pts);

    // 19. Clients dont les points accumulés >= valeur
    List<ClientResponse> findClientsBypointsAcumulesGreaterThanOrEqual(int pts);

    // 20. Clients dont les points accumulés entre deux valeurs
    List<ClientResponse> findClientsBypointsAcumulesBetween(int min, int max);

    List<ClientResponse> findClientsByCommandeArticleNom(String nomArticle);

    List<ClientResponse> findClientsByNomContainingAndArticleType(String nomStr, String typeArticle);
    void ajouterCommandeEtAffecterAClient(Commande c , String nomClient, String prenomClient);


    // AJOUTS : Signatures pour cascade methods
    ClientResponse addClientWithCascade(ClientRequest request);

    void deleteClientWithCascade(Long id);

    Optional<Client> findClientByIdWithCarteFidelite(Long id);


    List<Client> incrementerPts();
}