package tn.esprit._4twin6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit._4twin6.entities.CarteFidelite;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarteFideliteRepository extends JpaRepository<CarteFidelite, Long> {

    // 1. Trouver les cartes avec un nombre exact de points
    List<CarteFidelite> findByPointsAcumules(Integer pointsAcumules);

    // 2. Trouver les cartes créées à une date spécifique
    List<CarteFidelite> findByDateCreation(LocalDate dateCreation);

    // 3. Compter les cartes avec plus de X points
    long countByPointsAcumulesGreaterThan(Integer pointsAcumules);

    // 4. Supprimer les cartes créées avant une date
    @Modifying
    @Query("DELETE FROM CarteFidelite c WHERE c.dateCreation < :date")
    void deleteByDateCreationBefore(@Param("date") LocalDate date);

    // 5. Trouver les cartes avec des points dans une plage, créées après une date
    List<CarteFidelite> findByPointsAcumulesBetweenAndDateCreationAfter(Integer minPoints, Integer maxPoints, LocalDate dateCreation);

    // 6. Trouver les cartes avec au moins X points, triées par date de création
    List<CarteFidelite> findByPointsAcumulesGreaterThanEqualOrderByDateCreationAsc(Integer pointsAcumules);

    // 7. Trouver les cartes créées entre deux dates
    List<CarteFidelite> findByDateCreationBetween(LocalDate startDate, LocalDate endDate);

    // 8. Trouver les cartes avec peu de points OU créées avant une date
    @Query("SELECT c FROM CarteFidelite c WHERE c.pointsAcumules < :maxPoints OR c.dateCreation < :date")
    List<CarteFidelite> findByPointsAcumulesLessThanOrDateCreationBefore(@Param("maxPoints") Integer maxPoints, @Param("date") LocalDate date);

    // 9. Trouver la carte avec le plus de points
    @Query("SELECT c FROM CarteFidelite c ORDER BY c.pointsAcumules DESC")
    Optional<CarteFidelite> findTopByOrderByPointsAcumulesDesc();

    // 10. Trouver les cartes sans date de création
    List<CarteFidelite> findByDateCreationIsNull();

    // 11. Trouver les cartes avec des points accumulés renseignés
    List<CarteFidelite> findByPointsAcumulesIsNotNull();

    // 12. Trouver les cartes avec leur client propriétaire (par nom et prénom)
    @Query("SELECT c FROM CarteFidelite c JOIN FETCH c.client cl WHERE cl.nom = :nom AND cl.prenom = :prenom")
    List<CarteFidelite> findByClientNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);

    // 13. Trouver top 5 des cartes avec le plus de points
    List<CarteFidelite> findFirst5ByOrderByPointsAcumulesDesc();

}