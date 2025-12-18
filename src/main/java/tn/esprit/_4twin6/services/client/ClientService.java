package tn.esprit._4twin6.services.client;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit._4twin6.dto.ClientDTO.ClientRequest;
import tn.esprit._4twin6.dto.ClientDTO.ClientResponse;
import tn.esprit._4twin6.entities.Adresse;
import tn.esprit._4twin6.entities.CarteFidelite;
import tn.esprit._4twin6.entities.Client;
import tn.esprit._4twin6.entities.Commande;
import tn.esprit._4twin6.mapper.IClientMapper;
import tn.esprit._4twin6.repositories.AdresseRepository;
import tn.esprit._4twin6.repositories.ClientRepository;
import tn.esprit._4twin6.repositories.CommandeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService implements IClientServiceImpl {

    private final ClientRepository clientRepo;
    @Qualifier("IClientMapperImpl")
    private final IClientMapper clientMapper;
    private final CommandeRepository commandeRepo;
    private final AdresseRepository adresseRepo;

    @Override
    public ClientResponse addClient(ClientRequest request) {
        Client client = clientMapper.toEntity(request);
        Client saved = clientRepo.save(client);
        return clientMapper.toResponse(saved);
    }

    @Override
    public List<ClientResponse> saveClients(List<ClientRequest> requests) {
        List<Client> clients = requests.stream()
                .map(clientMapper::toEntity)
                .collect(Collectors.toList());
        return clientRepo.saveAll(clients).stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponse selectClientById(long id) {
        Optional<Client> client = clientRepo.findById(id);
        return client.map(clientMapper::toResponse).orElse(null);
    }

    @Override
    public List<ClientResponse> selectAllClients() {
        return clientRepo.findAll().stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteClientById(long id) {
        clientRepo.deleteById(id);
    }

    @Override
    public void deleteAllClients() {
        clientRepo.deleteAll();
    }

    @Override
    public long countingClients() {
        return clientRepo.count();
    }

    @Override
    public boolean verifClientById(long id) {
        return clientRepo.existsById(id);
    }

    // =============================
    //    JPQL QUERY METHODS
    // =============================

    @Override
    public List<ClientResponse> findClientsByNom(String nom) {
        return clientRepo.findByNom(nom)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> findClientsByPrenom(String prenom) {
        return clientRepo.findByPrenom(prenom)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponse findClientByNomAndPrenom(String nom, String prenom) {
        Client client = clientRepo.findByNomAndPrenom(nom, prenom);
        return client != null ? clientMapper.toResponse(client) : null;
    }

    @Override
    public boolean existsClientByNom(String nom) {
        return clientRepo.existsByNom(nom);
    }

    @Override
    public long countClientsByDateNaissanceAfter(LocalDate date) {
        return clientRepo.countByDateNaissanceAfter(date);
    }

    @Override
    public List<ClientResponse> findClientsByNomOrPrenomContaining(String str) {
        return clientRepo.findByNomOrPrenomContaining(str)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> findClientsByNomAndPrenomContaining(String str) {
        return clientRepo.findByNomAndPrenomContaining(str)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> findClientsByDateNaissanceBetween(LocalDate start, LocalDate end) {
        return clientRepo.findByDateNaissanceBetween(start, end)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> findClientsByNomStartingWithAndDateNaissanceBefore(String prefix, LocalDate date) {
        return clientRepo.findByNomStartingWithAndDateNaissanceBefore(prefix, date)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> findClientsByAdresseVille(String ville) {
        return clientRepo.findByAdresseVille(ville)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> findClientsByNomContainingOrderByPrenomAsc(String str) {
        return clientRepo.findByNomContainingOrderByPrenomAsc(str)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> findClientsByNomContainingOrderByPrenomDesc(String str) {
        return clientRepo.findByNomContainingOrderByPrenomDesc(str)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> findClientsByNomStartingWith(String letter) {
        return clientRepo.findByNomStartingWith(letter)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> findClientsByPrenomEndingWith(String suffix) {
        return clientRepo.findByPrenomEndingWith(suffix)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> findClientsByDateNaissanceIsNull() {
        return clientRepo.findByDateNaissanceIsNull()
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> findClientsByAdresseIsNotNull() {
        return clientRepo.findByAdresseIsNotNull()
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> findClientsByAdresseVilleIn(List<String> villes) {
        return clientRepo.findByAdresseVilleIn(villes)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    // 18. Clients dont les points accumulés > valeur
    @Override
    public List<ClientResponse> findClientsBypointsAcumulesGreaterThan(int pts) {
        return clientRepo.findBypointsAcumulesGreaterThan(pts)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    // 19. Clients dont les points accumulés >= valeur
    @Override
    public List<ClientResponse> findClientsBypointsAcumulesGreaterThanOrEqual(int pts) {
        return clientRepo.findBypointsAcumulesGreaterThanOrEqual(pts)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    // 20. Clients dont les points accumulés entre deux valeurs
    @Override
    public List<ClientResponse> findClientsBypointsAcumulesBetween(int min, int max) {
        return clientRepo.findBypointsAcumulesBetween(min, max)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> findClientsByCommandeArticleNom(String nomArticle) {
        return clientRepo.findByCommandeArticleNom(nomArticle)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> findClientsByNomContainingAndArticleType(String nomStr, String typeArticle) {
        return clientRepo.findByNomContainingAndArticleType(nomStr, typeArticle)
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void ajouterCommandeEtAffecterAClient(Commande c, String nomClient, String prenomClient) {
        c=commandeRepo.save(c);
        Client client = clientRepo.findByNomAndPrenom(nomClient, prenomClient);
        //parent ->commande ? child ->client ?
        c.setClient(client);
        commandeRepo.save(c);
    }

    // AJOUT : Méthode 1 - Ajout en Cascade (auto-crée Carte si absente)
    @Override
    @Transactional
    public ClientResponse addClientWithCascade(ClientRequest request) {
        Client client = clientMapper.toEntity(request);

        // Auto-créer Carte si null
        if (client.getCarteFidelite() == null) {
            CarteFidelite carte = CarteFidelite.builder()
                    .pointsAcumules(0)
                    .dateCreation(LocalDate.now())
                    .build();
            client.setCarteFidelite(carte);
            carte.setClient(client); // Bidirectionnel
        }

        Client saved = clientRepo.save(client); // Cascade persiste Carte
        return clientMapper.toResponse(saved);
    }

    // AJOUT : Méthode 2 - Suppression en Cascade
    @Override
    @Transactional
    public void deleteClientWithCascade(Long id) {
        Optional<Client> clientOpt = clientRepo.findByIdWithCarteFidelite(id); // Fetch avec Carte
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            // Unset pour trigger orphanRemoval
            if (client.getCarteFidelite() != null) {
                client.getCarteFidelite().setClient(null);
                client.setCarteFidelite(null);
            }
            clientRepo.delete(client); // Cascade supprime Carte
        }
    }

    // AJOUT : Implémentation de la query repo (pour complétude)
    @Override
    public Optional<Client> findClientByIdWithCarteFidelite(Long id) {
        return clientRepo.findByIdWithCarteFidelite(id);
    }




    @Override
    public List<Client> incrementerPts() {
        List<Client> list = clientRepo.selectByDateNaissance(LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        for (Client c : list) {
            int currentPoints = c.getCarteFidelite().getPointsAcumules();
            c.getCarteFidelite().setPointsAcumules((int) (currentPoints + (currentPoints * 0.1)));
            clientRepo.save(c);
        }
        return list;
    }
}