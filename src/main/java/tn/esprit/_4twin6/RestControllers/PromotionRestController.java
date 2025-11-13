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
import tn.esprit._4twin6.dto.PromotionDTO.PromotionRequest;
import tn.esprit._4twin6.dto.PromotionDTO.PromotionResponse;
import tn.esprit._4twin6.entities.Promotion;
import tn.esprit._4twin6.services.promotion.IPromotionServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Promotion Management", description = "Manage discounts and special offers")
@RequestMapping("promotions")
public class PromotionRestController {

    private IPromotionServiceImpl promotionService;

    // ✅ Add one promotion
    @PostMapping
    public PromotionResponse addPromotion(@RequestBody PromotionRequest request) {
        return promotionService.addPromotion(request);
    }

    // ✅ Add multiple promotions
    @PostMapping("/batch")
    public List<PromotionResponse> savePromotions(@RequestBody List<PromotionRequest> requests) {
        return promotionService.savePromotions(requests);
    }

    // ✅ Get all promotions
    @GetMapping
    public List<PromotionResponse> selectAllPromotions() {
        return promotionService.selectAllPromotions();
    }

    // ✅ Get promotion by ID
    @GetMapping("/{id}")
    public PromotionResponse selectPromotionById(@PathVariable long id) {
        return promotionService.selectPromotionById(id);
    }

    // ✅ Delete promotion by ID
    @DeleteMapping("/{id}")
    public void deletePromotionById(@PathVariable long id) {
        promotionService.deletePromotionById(id);
    }

    // ✅ Delete all promotions
    @DeleteMapping("/all")
    public void deleteAllPromotions() {
        promotionService.deleteAllPromotions();
    }

    // ✅ Count promotions
    @GetMapping("/count")
    public long countPromotions() {
        return promotionService.countingPromotions();
    }

    // ✅ Check if promotion exists
    @GetMapping("/exists/{id}")
    public boolean verifPromotionById(@PathVariable long id) {
        return promotionService.verifPromotionById(id);
    }
}
