package tn.esprit._4twin6.services.cartefidelite;

import tn.esprit._4twin6.dto.CarteFideliteDTO.CarteFideliteRequest;
import tn.esprit._4twin6.dto.CarteFideliteDTO.CarteFideliteResponse;
import tn.esprit._4twin6.entities.CarteFidelite;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ICartefideliteServiceImpl {

    // CRUD Methods
    CarteFideliteResponse addCarteFidelite(CarteFideliteRequest request);

    List<CarteFideliteResponse> saveCartesFidelite(List<CarteFideliteRequest> requests);

    CarteFideliteResponse selectCarteFideliteById(long id);

    List<CarteFideliteResponse> selectAllCartesFidelite();

    void deleteCarteFidelite(long id);

    void deleteAllCartesFidelite();

    long countingCartesFidelite();

    boolean verifCarteFideliteById(long id);

    // =============================
    //        QUERY METHODS
    // =============================

    // 1. Trouver les cartes avec un nombre exact de points
    List<CarteFideliteResponse> findCartesByPointsAcumules(Integer pointsAcumules);

    // 2. Trouver les cartes créées à une date spécifique
    List<CarteFideliteResponse> findCartesByDateCreation(LocalDate dateCreation);

    // 3. Compter les cartes avec plus de X points
    long countCartesByPointsAcumulesGreaterThan(Integer pointsAcumules);

    // 4. Supprimer les cartes créées avant une date
    void deleteCartesByDateCreationBefore(LocalDate date);

    // 5. Trouver les cartes avec des points dans une plage, créées après une date
    List<CarteFideliteResponse> findCartesByPointsAcumulesBetweenAndDateCreationAfter(Integer minPoints, Integer maxPoints, LocalDate dateCreation);

    // 6. Trouver les cartes avec au moins X points, triées par date de création
    List<CarteFideliteResponse> findCartesByPointsAcumulesGreaterThanEqualOrderByDateCreationAsc(Integer pointsAcumules);

    // 7. Trouver les cartes créées entre deux dates
    List<CarteFideliteResponse> findCartesByDateCreationBetween(LocalDate startDate, LocalDate endDate);

    // 8. Trouver les cartes avec peu de points OU créées avant une date
    List<CarteFideliteResponse> findCartesByPointsAcumulesLessThanOrDateCreationBefore(Integer maxPoints, LocalDate date);

    // 9. Trouver la carte avec le plus de points
    Optional<CarteFideliteResponse> findTopCarteByPointsAcumulesDesc();

    // 10. Trouver les cartes sans date de création
    List<CarteFideliteResponse> findCartesByDateCreationIsNull();

    // 11. Trouver les cartes avec des points accumulés renseignés
    List<CarteFideliteResponse> findCartesByPointsAcumulesIsNotNull();

    // 12. Trouver les cartes avec leur client propriétaire (par nom et prénom)
    List<CarteFideliteResponse> findCartesByClientNomAndPrenom(String nom, String prenom);

    // 13. Trouver top 5 des cartes avec le plus de points
    List<CarteFideliteResponse> findFirst5CartesByOrderByPointsAcumulesDesc();
    Integer getPointsAccumules(Long id);
}