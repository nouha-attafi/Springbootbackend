package tn.esprit._4twin6.services.promotion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit._4twin6.dto.PromotionDTO.PromotionRequest;
import tn.esprit._4twin6.dto.PromotionDTO.PromotionResponse;
import tn.esprit._4twin6.entities.Article;
import tn.esprit._4twin6.entities.Promotion;
import tn.esprit._4twin6.mapper.IPromotionMapper;
import tn.esprit._4twin6.repositories.ArticleRepository;
import tn.esprit._4twin6.repositories.PromotionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PromotionService implements IPromotionServiceImpl {

    private final PromotionRepository promoRepo;
    private final IPromotionMapper promoMapper;
    private final ArticleRepository articleRepo;

    // ✅ Add one promotion (DTO version)
    @Override
    public PromotionResponse addPromotion(PromotionRequest request) {
        Promotion promotion = promoMapper.toEntity(request);

        // Attach related articles (if provided)
        if (request.getArticleIds() != null && !request.getArticleIds().isEmpty()) {
            List<Article> articles = articleRepo.findAllById(request.getArticleIds());
            promotion.setArticles(articles);
        }

        Promotion saved = promoRepo.save(promotion);
        return promoMapper.toResponse(saved);
    }

    // ✅ Add multiple promotions
    @Override
    public List<PromotionResponse> savePromotions(List<PromotionRequest> requests) {
        List<Promotion> promotions = requests.stream()
                .map(dto -> {
                    Promotion p = promoMapper.toEntity(dto);
                    if (dto.getArticleIds() != null && !dto.getArticleIds().isEmpty()) {
                        List<Article> articles = articleRepo.findAllById(dto.getArticleIds());
                        p.setArticles(articles);
                    }
                    return p;
                })
                .collect(Collectors.toList());

        return promoRepo.saveAll(promotions).stream()
                .map(promoMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ✅ Get one promotion by ID
    @Override
    public PromotionResponse selectPromotionById(long id) {
        Optional<Promotion> promotionOpt = promoRepo.findById(id);
        return promotionOpt.map(promoMapper::toResponse).orElse(null);
    }

    // ✅ Get all promotions
    @Override
    public List<PromotionResponse> selectAllPromotions() {
        return promoRepo.findAll().stream()
                .map(promoMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ✅ Delete one promotion by ID
    @Override
    public void deletePromotionById(long id) {
        promoRepo.deleteById(id);
    }

    // ✅ Delete all promotions
    @Override
    public void deleteAllPromotions() {
        promoRepo.deleteAll();
    }

    // ✅ Count promotions
    @Override
    public long countingPromotions() {
        return promoRepo.count();
    }

    // ✅ Verify if promotion exists
    @Override
    public boolean verifPromotionById(long id) {
        return promoRepo.existsById(id);
    }
}
