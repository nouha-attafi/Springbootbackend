package tn.esprit._4twin6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit._4twin6.entities.Detail_Commande;

import java.util.List;

public interface Detail_CommandeRepository extends JpaRepository<Detail_Commande, Long> {

    // 1. Trouver les détails de commande par quantité exacte
    List<Detail_Commande> findByQuantiteArticle(Integer quantite);

    // 2. Trouver les détails par sous-total exact
    List<Detail_Commande> findBySousTotalDetailArticle(Double sousTotal);

    // 3. Compter les détails avec plus de X quantités
    long countByQuantiteArticleGreaterThan(Integer quantite);

    // 4. Vérifier l'existence de détails avec un sous-total élevé
    boolean existsBySousTotalDetailArticleGreaterThan(Double sousTotal);

    // 5. Trouver les détails avec une quantité dans une plage et un sous-total minimum
    List<Detail_Commande> findByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThanEqual(
            Integer minQuant, Integer maxQuant, Double minSousTotal);

    // 6. Trouver les détails avec un sous-total dans une plage, triés par quantité ASC
    List<Detail_Commande> findBySousTotalDetailArticleBetweenOrderByQuantiteArticleAsc(Double min, Double max);

    // 7. Trouver les détails avec un sous-total après promotion dans une plage
    List<Detail_Commande> findBySousTotalDetailArticleApresPromoBetween(Double min, Double max);

    // 8. Trouver les détails par quantité ou sous-total minimum
    @Query("SELECT d FROM Detail_Commande d WHERE d.quantiteArticle = :quantite OR d.sousTotalDetailArticle > :minSousTotal")
    List<Detail_Commande> findByQuantiteArticleOrSousTotalDetailArticleGreaterThan(
            @Param("quantite") Integer quantite, @Param("minSousTotal") Double minSousTotal);

    // 9. Trouver les 5 détails les plus chers
    List<Detail_Commande> findFirst5ByOrderBySousTotalDetailArticleDesc();

    // 10. Trouver les détails sans quantité renseignée
    List<Detail_Commande> findByQuantiteArticleIsNull();

    // 11. Trouver les détails avec un sous-total après promotion renseigné
    List<Detail_Commande> findBySousTotalDetailArticleApresPromoIsNotNull();

    // 12. Trouver les détails avec leur commande et article (avec fetch joins)
    @Query("SELECT d FROM Detail_Commande d JOIN FETCH d.commande JOIN FETCH d.article")
    List<Detail_Commande> findAllWithCommandeAndArticle();
}