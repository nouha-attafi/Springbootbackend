package tn.esprit._4twin6.RestControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit._4twin6.dto.AdresseDTO.AdresseRequest;
import tn.esprit._4twin6.dto.AdresseDTO.AdresseResponse;
import tn.esprit._4twin6.entities.Adresse;
import tn.esprit._4twin6.entities.Client;
import tn.esprit._4twin6.services.adresse.IAdresseServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Address Management", description = "Manage customer delivery addresses with full CRUD")
@RequestMapping("adresses")
public class AdresseRestController {

    private final IAdresseServiceImpl adresseService;  // No @Qualifier here!

    // Endpoints CRUD existants
    @GetMapping
    @Operation(summary = "Get all addresses")
    public List<AdresseResponse> selectAllAdresses() {
        return adresseService.selectAllAdresses();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get address by ID")
    public AdresseResponse selectAdresseById(@PathVariable long id) {
        return adresseService.selectAdresseById(id);
    }

    @PostMapping
    @Operation(summary = "Add new address")
    public AdresseResponse addAdresse(@RequestBody AdresseRequest adresseRequest) {
        return adresseService.addAdresse(adresseRequest);
    }

    @PostMapping("/batch")
    @Operation(summary = "Add multiple addresses")
    public List<AdresseResponse> saveAdresses(@RequestBody List<AdresseRequest> adresseRequests) {
        return adresseService.saveAdresses(adresseRequests);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete address by ID")
    public void deleteAdresseById(@PathVariable long id) {
        adresseService.deleteAdresse(id);
    }

    @DeleteMapping
    @Operation(summary = "Delete all addresses")
    public void deleteAllAdresses() {
        adresseService.deleteAllAdresses();
    }

    @GetMapping("/count")
    @Operation(summary = "Count all addresses")
    public long countAdresses() {
        return adresseService.countingAdresses();
    }

    @GetMapping("/exists/{id}")
    @Operation(summary = "Check if address exists by ID")
    public boolean verifAdresseById(@PathVariable long id) {
        return adresseService.verifAdresseById(id);
    }

    // Nouveaux endpoints pour les méthodes du repository (basés sur le PDF)
    // 1. Trouver toutes les adresses d'une ville spécifique
    @GetMapping("/byVille/{ville}")
    @Operation(summary = "Get addresses by city")
    public List<AdresseResponse> getAdressesByVille(@PathVariable String ville) {
        return adresseService.getAdressesByVille(ville);
    }

    // 2. Trouver les adresses par code postal exact
    @GetMapping("/byCodePostal/{codePostal}")
    @Operation(summary = "Get addresses by exact postal code")
    public List<AdresseResponse> getAdressesByCodePostal(@PathVariable int codePostal) {
        return adresseService.getAdressesByCodePostal(codePostal);
    }

    // 3. Compter le nombre d'adresses dans une ville
    @GetMapping("/countByVille/{ville}")
    @Operation(summary = "Count addresses by city")
    public long countAdressesByVille(@PathVariable String ville) {
        return adresseService.countAdressesByVille(ville);
    }

    // 4. Supprimer toutes les adresses d'une ville
    @DeleteMapping("/byVille/{ville}")
    @Operation(summary = "Delete all addresses by city")
    public void deleteAdressesByVille(@PathVariable String ville) {
        adresseService.deleteAdressesByVille(ville);
    }

    // 5. Trouver les adresses d'une ville avec un code postal spécifique
    @GetMapping("/byVilleAndCodePostal/{ville}/{codePostal}")
    @Operation(summary = "Get addresses by city and postal code")
    public List<AdresseResponse> getAdressesByVilleAndCodePostal(
            @PathVariable String ville, @PathVariable int codePostal) {
        return adresseService.getAdressesByVilleAndCodePostal(ville, codePostal);
    }

    // 6. Trouver les adresses dont la rue contient un mot, insensible à la casse de la ville
    @GetMapping("/byVilleIgnoreCaseAndRueContaining/{ville}/{mot}")
    @Operation(summary = "Get addresses by city (ignore case) and street containing word")
    public List<AdresseResponse> getAdressesByVilleIgnoreCaseAndRueContaining(
            @PathVariable String ville, @PathVariable String mot) {
        return adresseService.getAdressesByVilleIgnoreCaseAndRueContaining(ville, mot);
    }

    // 7. Trouver les adresses situées dans une liste de villes
    @GetMapping("/byVillesIn")
    @Operation(summary = "Get addresses by list of cities")
    public List<AdresseResponse> getAdressesByVillesIn(@RequestParam List<String> villes) {
        return adresseService.getAdressesByVillesIn(villes);
    }

    // 8. Trouver les adresses avec un code postal dans une plage spécifique
    @GetMapping("/byCodePostalBetween/{min}/{max}")
    @Operation(summary = "Get addresses by postal code range")
    public List<AdresseResponse> getAdressesByCodePostalBetween(
            @PathVariable int min, @PathVariable int max) {
        return adresseService.getAdressesByCodePostalBetween(min, max);
    }

    // 9. Trouver les adresses avec un code postal supérieur au code postal passé en paramètre
    @GetMapping("/byCodePostalGreaterThan/{codePostal}")
    @Operation(summary = "Get addresses with postal code > given")
    public List<AdresseResponse> getAdressesByCodePostalGreaterThan(@PathVariable int codePostal) {
        return adresseService.getAdressesByCodePostalGreaterThan(codePostal);
    }

    // 10. Trouver les adresses avec un code postal supérieur ou égal au code postal passé en paramètre
    @GetMapping("/byCodePostalGreaterThanEqual/{codePostal}")
    @Operation(summary = "Get addresses with postal code >= given")
    public List<AdresseResponse> getAdressesByCodePostalGreaterThanEqual(@PathVariable int codePostal) {
        return adresseService.getAdressesByCodePostalGreaterThanEqual(codePostal);
    }

    // 11. Trouver les adresses avec un code postal inférieur au code postal passé en paramètre
    @GetMapping("/byCodePostalLessThan/{codePostal}")
    @Operation(summary = "Get addresses with postal code < given")
    public List<AdresseResponse> getAdressesByCodePostalLessThan(@PathVariable int codePostal) {
        return adresseService.getAdressesByCodePostalLessThan(codePostal);
    }

    // 12. Trouver les adresses avec un code postal inférieur ou égal au code postal passé en paramètre
    @GetMapping("/byCodePostalLessThanEqual/{codePostal}")
    @Operation(summary = "Get addresses with postal code <= given")
    public List<AdresseResponse> getAdressesByCodePostalLessThanEqual(@PathVariable int codePostal) {
        return adresseService.getAdressesByCodePostalLessThanEqual(codePostal);
    }

    // 13. Trouver les adresses dont la rue commence par, dans une ville, triées par code postal
    @GetMapping("/byRueStartingWithAndVilleOrderByCodePostal/{rueStart}/{ville}")
    @Operation(summary = "Get addresses by street start, city, ordered by postal code")
    public List<AdresseResponse> getAdressesByRueStartingWithAndVilleOrderByCodePostal(
            @PathVariable String rueStart, @PathVariable String ville) {
        return adresseService.getAdressesByRueStartingWithAndVilleOrderByCodePostal(rueStart, ville);
    }

    // 14. Trouver les adresses dont le nom de rue commence par une chaîne spécifique
    @GetMapping("/byRueStartingWith/{rueStart}")
    @Operation(summary = "Get addresses by street starting with")
    public List<AdresseResponse> getAdressesByRueStartingWith(@PathVariable String rueStart) {
        return adresseService.getAdressesByRueStartingWith(rueStart);
    }

    // 15. Trouver les adresses dont le nom de ville se termine par une terminaison spécifique
    @GetMapping("/byVilleEndingWith/{villeEnd}")
    @Operation(summary = "Get addresses by city ending with")
    public List<AdresseResponse> getAdressesByVilleEndingWith(@PathVariable String villeEnd) {
        return adresseService.getAdressesByVilleEndingWith(villeEnd);
    }

    // 16. Trouver les adresses où le champ rue est null
    @GetMapping("/byRueIsNull")
    @Operation(summary = "Get addresses where street is null")
    public List<AdresseResponse> getAdressesByRueIsNull() {
        return adresseService.getAdressesByRueIsNull();
    }

    // 17. Trouver les adresses où la ville n'est pas null
    @GetMapping("/byVilleIsNotNull")
    @Operation(summary = "Get addresses where city is not null")
    public List<AdresseResponse> getAdressesByVilleIsNotNull() {
        return adresseService.getAdressesByVilleIsNotNull();
    }

    //@PostMapping("ajouterEtAffecterAdresseAClient")
  //  public void ajouterEtAffecterAdresseAClient(@RequestBody Adresse adresse,@RequestBody Client c) {
//service.ajouterEtAffecterAdresseAClient

    @PostMapping("ajouterEtAffecterAdresseAClient")
    public void ajouterEtAffecterAdresseAClient(@RequestBody Adresse adresse ,@RequestBody Client client)
    {
        adresseService.ajouterEtAffecterAdresseAClient(adresse,client);
    }

    }
