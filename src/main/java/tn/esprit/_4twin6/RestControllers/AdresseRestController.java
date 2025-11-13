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
import tn.esprit._4twin6.services.adresse.IAdresseServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Address Management", description = "Manage customer delivery addresses with full CRUD")
@RequestMapping("adresses")
public class AdresseRestController {

    private IAdresseServiceImpl adresseService;


    @GetMapping
    public List<AdresseResponse> selectAllAdresses() {
        return adresseService.selectAllAdresses();
    }


    @GetMapping("/{id}")
    public AdresseResponse selectAdresseById(@PathVariable long id) {
        return adresseService.selectAdresseById(id);
    }


    @PostMapping
    public AdresseResponse addAdresse(@RequestBody AdresseRequest adresseRequest) {
        return adresseService.addAdresse(adresseRequest);
    }


    @PostMapping("/batch")
    public List<AdresseResponse> saveAdresses(@RequestBody List<AdresseRequest> adresseRequests) {
        return adresseService.saveAdresses(adresseRequests);
    }


    @DeleteMapping("/{id}")
    public void deleteAdresseById(@PathVariable long id) {
        adresseService.deleteAdresse(id);
    }


    @DeleteMapping
    public void deleteAllAdresses() {
        adresseService.deleteAllAdresses();
    }


    @GetMapping("/count")
    public long countAdresses() {
        return adresseService.countingAdresses();
    }


    @GetMapping("/exists/{id}")
    public boolean verifAdresseById(@PathVariable long id) {
        return adresseService.verifAdresseById(id);
    }
}
