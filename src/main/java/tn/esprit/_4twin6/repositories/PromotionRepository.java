package tn.esprit._4twin6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit._4twin6.entities.Promotion;

import java.time.LocalDate;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    // 1. Trouver les promotions par pourcentage exact
    List<Promotion> findByPoucentagePromotion(String poucentage);

    // 2. Trouver les promotions par date de début
    List<Promotion> findByDateDebutPromotion(LocalDate dateDebut);

    // 3. Trouver les promotions par date de fin
    List<Promotion> findByDateFin(LocalDate dateFin);

    // 4. Vérifier l'existence d'une promotion par pourcentage
    boolean existsByPoucentagePromotion(String poucentage);

    // 5. Compter les promotions débutant après une date
    long countByDateDebutPromotionAfter(LocalDate date);

    // 6. Trouver les promotions actives à une date donnée
    @Query("SELECT p FROM Promotion p WHERE :date BETWEEN p.dateDebutPromotion AND COALESCE(p.dateFin, CURRENT_DATE)")
    List<Promotion> findActivePromotionsAtDate(@Param("date") LocalDate date);

    // 7. Trouver les promotions avec un pourcentage spécifique débutant dans une période
    @Query("SELECT p FROM Promotion p WHERE p.poucentagePromotion = :poucentage AND p.dateDebutPromotion BETWEEN :start AND :end")
    List<Promotion> findByPoucentagePromotionAndDateDebutPromotionBetween(
            @Param("poucentage") String poucentage,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end);

    // 8. Trouver les promotions valides à une date spécifique
    @Query("SELECT p FROM Promotion p WHERE :date >= p.dateDebutPromotion AND (p.dateFin IS NULL OR :date <= p.dateFin)")
    List<Promotion> findValidPromotionsAtDate(@Param("date") LocalDate date);

    // 9. Trouver les promotions avec certains pourcentages, triées par date de début
    List<Promotion> findByPoucentagePromotionInOrderByDateDebutPromotionAsc(List<String> pourcentages);

    // 10. Trouver les promotions actives triées par pourcentage
    @Query("SELECT p FROM Promotion p WHERE CURRENT_DATE BETWEEN p.dateDebutPromotion AND COALESCE(p.dateFin, CURRENT_DATE) ORDER BY CAST(p.poucentagePromotion AS double) ASC")
    List<Promotion> findActivePromotionsOrderByPoucentagePromotionAsc();

    // 11. Trouver les promotions sans date de fin
    List<Promotion> findByDateFinIsNull();

    // 12. Trouver les promotions avec un pourcentage renseigné
    List<Promotion> findByPoucentagePromotionIsNotNull();

    // 13. Trouver les promotions avec leurs articles associés
    @Query("SELECT DISTINCT p FROM Promotion p LEFT JOIN FETCH p.articles a")
    List<Promotion> findAllWithArticles();

    // 14. Trouver les promotions expirées
    @Query("SELECT p FROM Promotion p WHERE p.dateFin IS NOT NULL AND p.dateFin < CURRENT_DATE")
    List<Promotion> findExpiredPromotions();
}