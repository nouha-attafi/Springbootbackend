package tn.esprit._4twin6.services.detailcommande;

import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeRequest;
import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeResponse;
import tn.esprit._4twin6.entities.Detail_Commande;

import java.util.List;

public interface IDetailCommandeServiceImpl {

    // CRUD Methods
    DetailCommandeResponse addDetailCommande(DetailCommandeRequest request);

    List<DetailCommandeResponse> saveDetailsCommande(List<DetailCommandeRequest> requests);

    DetailCommandeResponse selectDetailCommandeById(long id);

    List<DetailCommandeResponse> selectAllDetailsCommande();

    void deleteDetailCommandeById(long id);

    void deleteAllDetailsCommande();

    long countingDetailsCommande();

    boolean verifDetailCommandeById(long id);

    // =============================
    //        QUERY METHODS
    // =============================

    // 1. Trouver les détails de commande par quantité exacte
    List<DetailCommandeResponse> findDetailsByQuantiteArticle(Integer quantite);

    // 2. Trouver les détails par sous-total exact
    List<DetailCommandeResponse> findDetailsBySousTotalDetailArticle(Double sousTotal);

    // 3. Compter les détails avec plus de X quantités
    long countDetailsByQuantiteArticleGreaterThan(Integer quantite);

    // 4. Vérifier l'existence de détails avec un sous-total élevé
    boolean existsDetailsBySousTotalDetailArticleGreaterThan(Double sousTotal);

    // 5. Trouver les détails avec une quantité dans une plage et un sous-total minimum
    List<DetailCommandeResponse> findDetailsByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThanEqual(
            Integer minQuant, Integer maxQuant, Double minSousTotal);

    // 6. Trouver les détails avec un sous-total dans une plage, triés par quantité ASC
    List<DetailCommandeResponse> findDetailsBySousTotalDetailArticleBetweenOrderByQuantiteArticleAsc(Double min, Double max);

    // 7. Trouver les détails avec un sous-total après promotion dans une plage
    List<DetailCommandeResponse> findDetailsBySousTotalDetailArticleApresPromoBetween(Double min, Double max);

    // 8. Trouver les détails par quantité ou sous-total minimum
    List<DetailCommandeResponse> findDetailsByQuantiteArticleOrSousTotalDetailArticleGreaterThan(
            Integer quantite, Double minSousTotal);

    // 9. Trouver les 5 détails les plus chers
    List<DetailCommandeResponse> findFirst5DetailsByOrderBySousTotalDetailArticleDesc();

    // 10. Trouver les détails sans quantité renseignée
    List<DetailCommandeResponse> findDetailsByQuantiteArticleIsNull();

    // 11. Trouver les détails avec un sous-total après promotion renseigné
    List<DetailCommandeResponse> findDetailsBySousTotalDetailArticleApresPromoIsNotNull();

    // 12. Trouver les détails avec leur commande et article
    List<DetailCommandeResponse> findAllDetailsWithCommandeAndArticle();
}