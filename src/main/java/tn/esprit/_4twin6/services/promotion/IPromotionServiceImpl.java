package tn.esprit._4twin6.services.promotion;

import tn.esprit._4twin6.dto.PromotionDTO.PromotionRequest;
import tn.esprit._4twin6.dto.PromotionDTO.PromotionResponse;
import tn.esprit._4twin6.entities.Promotion;

import java.time.LocalDate;
import java.util.List;

public interface IPromotionServiceImpl {

    // CRUD Methods
    PromotionResponse addPromotion(PromotionRequest request);

    List<PromotionResponse> savePromotions(List<PromotionRequest> requests);

    PromotionResponse selectPromotionById(long id);

    List<PromotionResponse> selectAllPromotions();

    void deletePromotionById(long id);

    void deleteAllPromotions();

    long countingPromotions();

    boolean verifPromotionById(long id);

    // =============================
    //        QUERY METHODS
    // =============================

    // 1. Trouver les promotions par pourcentage exact
    List<PromotionResponse> findPromotionsByPoucentagePromotion(String poucentage);

    // 2. Trouver les promotions par date de début
    List<PromotionResponse> findPromotionsByDateDebutPromotion(LocalDate dateDebut);

    // 3. Trouver les promotions par date de fin
    List<PromotionResponse> findPromotionsByDateFin(LocalDate dateFin);

    // 4. Vérifier l'existence d'une promotion par pourcentage
    boolean existsPromotionByPoucentagePromotion(String poucentage);

    // 5. Compter les promotions débutant après une date
    long countPromotionsByDateDebutPromotionAfter(LocalDate date);

    // 6. Trouver les promotions actives à une date donnée
    List<PromotionResponse> findActivePromotionsAtDate(LocalDate date);

    // 7. Trouver les promotions avec un pourcentage spécifique débutant dans une période
    List<PromotionResponse> findPromotionsByPoucentagePromotionAndDateDebutPromotionBetween(String poucentage, LocalDate start, LocalDate end);

    // 8. Trouver les promotions valides à une date spécifique
    List<PromotionResponse> findValidPromotionsAtDate(LocalDate date);

    // 9. Trouver les promotions avec certains pourcentages, triées par date de début
    List<PromotionResponse> findPromotionsByPoucentagePromotionInOrderByDateDebutPromotionAsc(List<String> pourcentages);

    // 10. Trouver les promotions actives triées par pourcentage
    List<PromotionResponse> findActivePromotionsOrderByPoucentagePromotionAsc();

    // 11. Trouver les promotions sans date de fin
    List<PromotionResponse> findPromotionsByDateFinIsNull();

    // 12. Trouver les promotions avec un pourcentage renseigné
    List<PromotionResponse> findPromotionsByPoucentagePromotionIsNotNull();

    // 13. Trouver les promotions avec leurs articles associés
    List<PromotionResponse> findAllPromotionsWithArticles();

    // 14. Trouver les promotions expirées
    List<PromotionResponse> findExpiredPromotions();

    void affecterPromotionAArticle(long idArticle, long idPromo);

    void desaffecterPromotionAArticle(long idArticle, long idPromo);

  void ajouterPromotionEtAffecterAArticle(Promotion p, Long idArticle);


}
