package tn.esprit._4twin6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit._4twin6.entities.Client;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // 1. Trouver tous les clients par nom de famille
    List<Client> findByNom(@Param("nom") String nom);

    // 2. Trouver les clients par prénom
    List<Client> findByPrenom(@Param("prenom") String prenom);

    // 3. Trouver un client spécifique par nom et prénom
    Client findByNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);

    // 4. Vérifier si un client existe par son nom
    boolean existsByNom(@Param("nom") String nom);

    // 5. Compter les clients nés après une certaine date
    long countByDateNaissanceAfter(@Param("date") LocalDate date);

    // 6. Trouver les clients dont le nom ou le prénom contient une chaîne
    @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :str, '%')) OR LOWER(c.prenom) LIKE LOWER(CONCAT('%', :str, '%'))")
    List<Client> findByNomOrPrenomContaining(@Param("str") String str);

    // 7. Trouver les clients dont le nom et le prénom contient une chaîne
    @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :str, '%')) AND LOWER(c.prenom) LIKE LOWER(CONCAT('%', :str, '%'))")
    List<Client> findByNomAndPrenomContaining(@Param("str") String str);

    // 8. Trouver les clients nés entre deux dates
    List<Client> findByDateNaissanceBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);

    // 9. Trouver les clients dont le nom commence par et nés avant une date
    List<Client> findByNomStartingWithAndDateNaissanceBefore(@Param("prefix") String prefix, @Param("date") LocalDate date);

    // 10. Trouver les clients par ville de leur adresse (jointure implicite)
    List<Client> findByAdresseVille(@Param("ville") String ville);

    // 11. Clients par nom contient une chaine triés par prénom ASC
    List<Client> findByNomContainingOrderByPrenomAsc(@Param("str") String str);

    // 12. Clients par nom contient une chaine triés par prénom DESC
    List<Client> findByNomContainingOrderByPrenomDesc(@Param("str") String str);

    // 13. Clients dont le nom commence par une lettre spécifique
    List<Client> findByNomStartingWith(@Param("letter") String letter);

    // 14. Clients dont le prénom se termine par une terminaison
    List<Client> findByPrenomEndingWith(@Param("suffix") String suffix);

    // 15. Clients sans date de naissance renseignée
    @Query("SELECT c FROM Client c WHERE c.dateNaissance IS NULL")
    List<Client> findByDateNaissanceIsNull();

    // 16. Clients avec une adresse renseignée
    List<Client> findByAdresseIsNotNull();

    // 17. Clients des plusieurs villes
    List<Client> findByAdresseVilleIn(@Param("villes") List<String> villes);

    // 18. Clients dont les ptsAccumules > valeur
    @Query("SELECT DISTINCT c FROM Client c JOIN c.carteFidelite cf WHERE cf.pointsAcumules > :pts")
    List<Client> findBypointsAcumulesGreaterThan(@Param("pts") int pts);

    // 19. Clients dont les ptsAccumules >= valeur
    @Query("SELECT DISTINCT c FROM Client c JOIN c.carteFidelite cf WHERE cf.pointsAcumules >= :pts")
    List<Client> findBypointsAcumulesGreaterThanOrEqual(@Param("pts") int pts);

    // 20. Clients dont les ptsAccumules entre deux valeurs
    @Query("SELECT DISTINCT c FROM Client c JOIN c.carteFidelite cf WHERE cf.pointsAcumules BETWEEN :min AND :max")
    List<Client> findBypointsAcumulesBetween(@Param("min") int min, @Param("max") int max);

    // 21. Clients ayant commandé un article par son nom
    @Query("SELECT DISTINCT c FROM Client c JOIN c.commandes com JOIN com.detailCommandes lc JOIN lc.article a WHERE a.nomArticle = :nomArticle")
    List<Client> findByCommandeArticleNom(@Param("nomArticle") String nomArticle);

    // 22. Clients par nom contenant ET type d'article commandé
    @Query("SELECT DISTINCT c FROM Client c JOIN c.commandes com JOIN com.detailCommandes lc JOIN lc.article a WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :nomStr, '%')) AND a.typeArticle = :typeArticle")
    List<Client> findByNomContainingAndArticleType(@Param("nomStr") String nomStr, @Param("typeArticle") String typeArticle);

    // Recherche générale par keyword (nom, prénom, ville)
    @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(c.prenom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(c.adresse.ville) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Client> findByKeyword(@Param("keyword") String keyword);

    // AJOUT : Méthode pour fetch Client avec CarteFidelite jointe (pour cascade delete)
    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.carteFidelite WHERE c.idClient = :id")
    Optional<Client> findByIdWithCarteFidelite(@Param("id") Long id);

    @Query(value = "SELECT c FROM T_CLIENT c WHERE" +
            " EXTRACT(DAY FROM c.date_naissance) =?2 "+
            "AND EXTRACT(MONTH FROM c.date_naissance) =71 ", nativeQuery = true)
    List<Client> selectByDateNaissance(int month, int day);
}