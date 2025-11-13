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
import tn.esprit._4twin6.dto.CarteFideliteDTO.CarteFideliteRequest;
import tn.esprit._4twin6.dto.CarteFideliteDTO.CarteFideliteResponse;
import tn.esprit._4twin6.entities.CarteFidelite;
import tn.esprit._4twin6.services.cartefidelite.ICartefideliteServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Loyalty Card Management", description = "Manage customer loyalty cards and points")
@RequestMapping("cartesfidelite")
public class CartefideliteRestController {

    private ICartefideliteServiceImpl carteFideliteService;

    // ✅ Get all cartes
    @GetMapping
    public List<CarteFideliteResponse> selectAllCartesFidelite() {
        return carteFideliteService.selectAllCartesFidelite();
    }

    // ✅ Get carte by ID
    @GetMapping("/{id}")
    public CarteFideliteResponse selectCarteFideliteById(@PathVariable long id) {
        return carteFideliteService.selectCarteFideliteById(id);
    }

    // ✅ Add one carte
    @PostMapping
    public CarteFideliteResponse addCarteFidelite(@RequestBody CarteFideliteRequest request) {
        return carteFideliteService.addCarteFidelite(request);
    }

    // ✅ Add multiple cartes
    @PostMapping("/batch")
    public List<CarteFideliteResponse> saveCartesFidelite(@RequestBody List<CarteFideliteRequest> requests) {
        return carteFideliteService.saveCartesFidelite(requests);
    }

    // ✅ Delete one carte by ID
    @DeleteMapping("/{id}")
    public void deleteCarteFidelite(@PathVariable long id) {
        carteFideliteService.deleteCarteFidelite(id);
    }

    // ✅ Delete all cartes
    @DeleteMapping
    public void deleteAllCartesFidelite() {
        carteFideliteService.deleteAllCartesFidelite();
    }

    // ✅ Count all cartes
    @GetMapping("/count")
    public long countCartesFidelite() {
        return carteFideliteService.countingCartesFidelite();
    }

    // ✅ Check if carte exists by ID
    @GetMapping("/exists/{id}")
    public boolean verifCarteFideliteById(@PathVariable long id) {
        return carteFideliteService.verifCarteFideliteById(id);
    }
}