package tn.esprit._4twin6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit._4twin6.entities.Commande;
import tn.esprit._4twin6.enums.StatusCommande;

import java.time.LocalDate;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    // 1. Trouver toutes les commandes par date exacte
    @Query("SELECT c FROM Commande c WHERE c.dateCommande = :date")
    List<Commande> findByDateCommande(@Param("date") LocalDate date);

    // 2. Trouver les commandes par total exact
    @Query("SELECT c FROM Commande c WHERE c.totalCommande = :total")
    List<Commande> findByTotalCommande(@Param("total") float total);

    // 3. Compter le nombre de commandes pour une date spécifique
    @Query("SELECT COUNT(c) FROM Commande c WHERE c.dateCommande = :date")
    long countByDateCommande(@Param("date") LocalDate date);

    // 4. Supprimer toutes les commandes pour une date spécifique
    @Modifying
    @Query("DELETE FROM Commande c WHERE c.dateCommande = :date")
    void deleteByDateCommande(@Param("date") LocalDate date);

    // 5. Trouver les commandes pour une date avec un total spécifique
    @Query("SELECT c FROM Commande c WHERE c.dateCommande = :date AND c.totalCommande = :total")
    List<Commande> findByDateCommandeAndTotalCommande(@Param("date") LocalDate date, @Param("total") float total);

    // 6. Trouver les commandes pour un status et nom client contenant un mot (insensible à la casse)
    @Query("SELECT c FROM Commande c JOIN c.client cl WHERE c.statusCommande = :status AND LOWER(cl.nom) LIKE LOWER(CONCAT('%', :mot, '%'))")
    List<Commande> findByStatusCommandeAndClientNomContainingIgnoreCase(@Param("status") StatusCommande status, @Param("mot") String mot);

    // 7. Trouver les commandes pour une liste de status
    @Query("SELECT c FROM Commande c WHERE c.statusCommande IN :statuses")
    List<Commande> findByStatusCommandeIn(@Param("statuses") List<StatusCommande> statuses);

    // 8. Trouver les commandes avec un total dans une plage spécifique
    @Query("SELECT c FROM Commande c WHERE c.totalCommande BETWEEN :min AND :max")
    List<Commande> findByTotalCommandeBetween(@Param("min") float min, @Param("max") float max);

    // 9. Trouver les commandes avec un total supérieur au total passé en paramètre
    @Query("SELECT c FROM Commande c WHERE c.totalCommande > :total")
    List<Commande> findByTotalCommandeGreaterThan(@Param("total") float total);

    // 10. Trouver les commandes avec un total supérieur ou égal au total passé en paramètre
    @Query("SELECT c FROM Commande c WHERE c.totalCommande >= :total")
    List<Commande> findByTotalCommandeGreaterThanEqual(@Param("total") float total);

    // 11. Trouver les commandes avec un total inférieur au total passé en paramètre
    @Query("SELECT c FROM Commande c WHERE c.totalCommande < :total")
    List<Commande> findByTotalCommandeLessThan(@Param("total") float total);

    // 12. Trouver les commandes avec un total inférieur ou égal au total passé en paramètre
    @Query("SELECT c FROM Commande c WHERE c.totalCommande <= :total")
    List<Commande> findByTotalCommandeLessThanEqual(@Param("total") float total);

    // 13. Trouver les commandes dont le nom client commence par, pour un status, triées par total
    @Query("SELECT c FROM Commande c JOIN c.client cl WHERE cl.nom LIKE CONCAT(:nomStart, '%') AND c.statusCommande = :status ORDER BY c.totalCommande")
    List<Commande> findByClientNomStartingWithAndStatusCommandeOrderByTotalCommande(@Param("nomStart") String nomStart, @Param("status") StatusCommande status);

    // 14. Trouver les commandes dont le prénom client commence par une chaîne spécifique
    @Query("SELECT c FROM Commande c JOIN c.client cl WHERE cl.prenom LIKE CONCAT(:prenomStart, '%')")
    List<Commande> findByClientPrenomStartingWith(@Param("prenomStart") String prenomStart);

    // 15. Trouver les commandes dont le nom client se termine par une terminaison spécifique
    @Query("SELECT c FROM Commande c JOIN c.client cl WHERE cl.nom LIKE CONCAT('%', :nomEnd)")
    List<Commande> findByClientNomEndingWith(@Param("nomEnd") String nomEnd);

    // 16. Trouver les commandes où la date est null
    @Query("SELECT c FROM Commande c WHERE c.dateCommande IS NULL")
    List<Commande> findByDateCommandeIsNull();

    // 17. Trouver les commandes où le status n'est pas null
    @Query("SELECT c FROM Commande c WHERE c.statusCommande IS NOT NULL")
    List<Commande> findByStatusCommandeIsNotNull();
}