package tn.esprit._4twin6.services.adresse;

import tn.esprit._4twin6.dto.AdresseDTO.AdresseRequest;
import tn.esprit._4twin6.dto.AdresseDTO.AdresseResponse;
import tn.esprit._4twin6.entities.Adresse;
import tn.esprit._4twin6.entities.Client;

import java.util.List;

public interface IAdresseServiceImpl {

        // Méthodes CRUD existantes
        AdresseResponse addAdresse(AdresseRequest request);
        List<AdresseResponse> saveAdresses(List<AdresseRequest> requests);
        AdresseResponse selectAdresseById(long id);
        List<AdresseResponse> selectAllAdresses();
        void deleteAdresse(long id);
        void deleteAllAdresses();
        long countingAdresses();
        boolean verifAdresseById(long id);

        // Nouvelles méthodes pour AdresseRepository
        // 1. Trouver toutes les adresses d'une ville spécifique
        List<AdresseResponse> getAdressesByVille(String ville);

        // 2. Trouver les adresses par code postal exact
        List<AdresseResponse> getAdressesByCodePostal(int codePostal);

        // 3. Compter le nombre d'adresses dans une ville
        long countAdressesByVille(String ville);

        // 4. Supprimer toutes les adresses d'une ville
        void deleteAdressesByVille(String ville);

        // 5. Trouver les adresses d'une ville avec un code postal spécifique
        List<AdresseResponse> getAdressesByVilleAndCodePostal(String ville, int codePostal);

        // 6. Trouver les adresses dont la rue contient un mot, insensible à la casse de la ville
        List<AdresseResponse> getAdressesByVilleIgnoreCaseAndRueContaining(String ville, String mot);

        // 7. Trouver les adresses situées dans une liste de villes
        List<AdresseResponse> getAdressesByVillesIn(List<String> villes);

        // 8. Trouver les adresses avec un code postal dans une plage spécifique
        List<AdresseResponse> getAdressesByCodePostalBetween(int min, int max);

        // 9. Trouver les adresses avec un code postal supérieur au code postal passé en paramètre
        List<AdresseResponse> getAdressesByCodePostalGreaterThan(int codePostal);

        // 10. Trouver les adresses avec un code postal supérieur ou égal au code postal passé en paramètre
        List<AdresseResponse> getAdressesByCodePostalGreaterThanEqual(int codePostal);

        // 11. Trouver les adresses avec un code postal inférieur au code postal passé en paramètre
        List<AdresseResponse> getAdressesByCodePostalLessThan(int codePostal);

        // 12. Trouver les adresses avec un code postal inférieur ou égal au code postal passé en paramètre
        List<AdresseResponse> getAdressesByCodePostalLessThanEqual(int codePostal);

        // 13. Trouver les adresses dont la rue commence par, dans une ville, triées par code postal
        List<AdresseResponse> getAdressesByRueStartingWithAndVilleOrderByCodePostal(String rueStart, String ville);

        // 14. Trouver les adresses dont le nom de rue commence par une chaîne spécifique
        List<AdresseResponse> getAdressesByRueStartingWith(String rueStart);

        // 15. Trouver les adresses dont le nom de ville se termine par une terminaison spécifique
        List<AdresseResponse> getAdressesByVilleEndingWith(String villeEnd);

        // 16. Trouver les adresses où le champ rue est null
        List<AdresseResponse> getAdressesByRueIsNull();

        // 17. Trouver les adresses où la ville n'est pas null
        List<AdresseResponse> getAdressesByVilleIsNotNull();
        void ajouterEtAffecterAdresseAClient(Adresse adresse, Client c);

}