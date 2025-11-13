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
import tn.esprit._4twin6.dto.CommandeDTO.CommandeRequest;
import tn.esprit._4twin6.dto.CommandeDTO.CommandeResponse;
import tn.esprit._4twin6.entities.Commande;
import tn.esprit._4twin6.services.commande.ICommandeServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Order Management", description = "Manage customer orders and status")
@RequestMapping("commandes")
public class CommandeRestController {

    private ICommandeServiceImpl commandeService;


    // ✅ Get all commandes
    @GetMapping
    public List<CommandeResponse> selectAllCommandes() {
        return commandeService.selectAllCommandes();
    }

    // ✅ Get commande by ID
    @GetMapping("/{id}")
    public CommandeResponse selectCommandeById(@PathVariable long id) {
        return commandeService.selectCommandeById(id);
    }

    // ✅ Add one commande
    @PostMapping
    public CommandeResponse addCommande(@RequestBody CommandeRequest request) {
        return commandeService.addCommande(request);
    }

    // ✅ Add multiple commandes
    @PostMapping("/batch")
    public List<CommandeResponse> saveCommandes(@RequestBody List<CommandeRequest> requests) {
        return commandeService.saveCommandes(requests);
    }

    // ✅ Delete commande by ID
    @DeleteMapping("/{id}")
    public void deleteCommandeById(@PathVariable long id) {
        commandeService.deleteCommandeById(id);
    }

    // ✅ Delete all commandes
    @DeleteMapping
    public void deleteAllCommandes() {
        commandeService.deleteAllCommandes();
    }

    // ✅ Count all commandes
    @GetMapping("/count")
    public long countCommandes() {
        return commandeService.countingCommandes();
    }

    // ✅ Check if commande exists by ID
    @GetMapping("/exists/{id}")
    public boolean verifCommandeById(@PathVariable long id) {
        return commandeService.verifCommandeById(id);
    }
}
