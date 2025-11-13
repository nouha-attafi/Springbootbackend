package tn.esprit._4twin6.services.promotion;

import tn.esprit._4twin6.dto.PromotionDTO.PromotionRequest;
import tn.esprit._4twin6.dto.PromotionDTO.PromotionResponse;
import tn.esprit._4twin6.entities.Promotion;
import java.util.List;

public interface IPromotionServiceImpl {
    // ✅ Add one promotion
    PromotionResponse addPromotion(PromotionRequest request);

    // ✅ Add multiple promotions
    List<PromotionResponse> savePromotions(List<PromotionRequest> requests);

    // ✅ Get one promotion by ID
    PromotionResponse selectPromotionById(long id);

    // ✅ Get all promotions
    List<PromotionResponse> selectAllPromotions();

    // ✅ Delete one promotion by ID
    void deletePromotionById(long id);

    // ✅ Delete all promotions
    void deleteAllPromotions();

    // ✅ Count all promotions
    long countingPromotions();

    // ✅ Check if a promotion exists
    boolean verifPromotionById(long id);
}
