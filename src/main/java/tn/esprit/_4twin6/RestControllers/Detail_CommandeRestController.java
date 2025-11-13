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
import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeRequest;
import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeResponse;
import tn.esprit._4twin6.entities.Detail_Commande;
import tn.esprit._4twin6.services.detailcommande.IDetailCommandeServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Order Detail Management", description = "Manage line items in orders")
@RequestMapping("details_commandes")
public class Detail_CommandeRestController {

    private IDetailCommandeServiceImpl detailService;

    // ✅ Get all detail commandes
    @GetMapping
    public List<DetailCommandeResponse> getAllDetails() {
        return detailService.selectAllDetailsCommande();
    }

    // ✅ Get detail commande by ID
    @GetMapping("/{id}")
    public DetailCommandeResponse getDetailById(@PathVariable long id) {
        return detailService.selectDetailCommandeById(id);
    }

    // ✅ Add one detail commande
    @PostMapping
    public DetailCommandeResponse addDetail(@RequestBody DetailCommandeRequest request) {
        return detailService.addDetailCommande(request);
    }

    // ✅ Add multiple detail commandes
    @PostMapping("/batch")
    public List<DetailCommandeResponse> addDetailsBatch(@RequestBody List<DetailCommandeRequest> requests) {
        return detailService.saveDetailsCommande(requests);
    }

    // ✅ Delete detail commande by ID
    @DeleteMapping("/{id}")
    public void deleteDetailById(@PathVariable long id) {
        detailService.deleteDetailCommandeById(id);
    }

    // ✅ Delete all detail commandes
    @DeleteMapping
    public void deleteAllDetails() {
        detailService.deleteAllDetailsCommande();
    }

    // ✅ Count all detail commandes
    @GetMapping("/count")
    public long countDetails() {
        return detailService.countingDetailsCommande();
    }

    // ✅ Check if detail commande exists by ID
    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable long id) {
        return detailService.verifDetailCommandeById(id);
    }
}
