package tn.esprit._4twin6.services.promotion;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit._4twin6.dto.PromotionDTO.PromotionRequest;
import tn.esprit._4twin6.dto.PromotionDTO.PromotionResponse;
import tn.esprit._4twin6.entities.Article;
import tn.esprit._4twin6.entities.Promotion;
import tn.esprit._4twin6.mapper.IPromotionMapper;
import tn.esprit._4twin6.repositories.ArticleRepository;
import tn.esprit._4twin6.repositories.PromotionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PromotionService implements IPromotionServiceImpl {

    private final PromotionRepository promoRepo;

    @Qualifier("IPromotionMapperImpl")
    private final IPromotionMapper promoMapper;

    private final ArticleRepository articleRepo;

    // CRUD Methods
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

    @Override
    public PromotionResponse selectPromotionById(long id) {
        Optional<Promotion> promo = promoRepo.findById(id);
        return promo.map(promoMapper::toResponse).orElse(null);
    }

    @Override
    public List<PromotionResponse> selectAllPromotions() {
        return promoRepo.findAll().stream()
                .map(promoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePromotionById(long id) {
        promoRepo.deleteById(id);
    }

    @Override
    public void deleteAllPromotions() {
        promoRepo.deleteAll();
    }

    @Override
    public long countingPromotions() {
        return promoRepo.count();
    }

    @Override
    public boolean verifPromotionById(long id) {
        return promoRepo.existsById(id);
    }

    // =============================
    //        QUERY METHODS
    // =============================

    @Override
    public List<PromotionResponse> findPromotionsByPoucentagePromotion(String poucentage) {
        return promoRepo.findByPoucentagePromotion(poucentage).stream()
                .map(promoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PromotionResponse> findPromotionsByDateDebutPromotion(LocalDate dateDebut) {
        return promoRepo.findByDateDebutPromotion(dateDebut).stream()
                .map(promoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PromotionResponse> findPromotionsByDateFin(LocalDate dateFin) {
        return promoRepo.findByDateFin(dateFin).stream()
                .map(promoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsPromotionByPoucentagePromotion(String poucentage) {
        return promoRepo.existsByPoucentagePromotion(poucentage);
    }

    @Override
    public long countPromotionsByDateDebutPromotionAfter(LocalDate date) {
        return promoRepo.countByDateDebutPromotionAfter(date);
    }

    @Override
    public List<PromotionResponse> findActivePromotionsAtDate(LocalDate date) {
        return promoRepo.findActivePromotionsAtDate(date).stream()
                .map(promoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PromotionResponse> findPromotionsByPoucentagePromotionAndDateDebutPromotionBetween(String poucentage, LocalDate start, LocalDate end) {
        return promoRepo.findByPoucentagePromotionAndDateDebutPromotionBetween(poucentage, start, end).stream()
                .map(promoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PromotionResponse> findValidPromotionsAtDate(LocalDate date) {
        return promoRepo.findValidPromotionsAtDate(date).stream()
                .map(promoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PromotionResponse> findPromotionsByPoucentagePromotionInOrderByDateDebutPromotionAsc(List<String> pourcentages) {
        return promoRepo.findByPoucentagePromotionInOrderByDateDebutPromotionAsc(pourcentages).stream()
                .map(promoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PromotionResponse> findActivePromotionsOrderByPoucentagePromotionAsc() {
        return promoRepo.findActivePromotionsOrderByPoucentagePromotionAsc().stream()
                .map(promoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PromotionResponse> findPromotionsByDateFinIsNull() {
        return promoRepo.findByDateFinIsNull().stream()
                .map(promoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PromotionResponse> findPromotionsByPoucentagePromotionIsNotNull() {
        return promoRepo.findByPoucentagePromotionIsNotNull().stream()
                .map(promoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PromotionResponse> findAllPromotionsWithArticles() {
        return promoRepo.findAllWithArticles().stream()
                .map(promoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PromotionResponse> findExpiredPromotions() {
        return promoRepo.findExpiredPromotions().stream()
                .map(promoMapper::toResponse)
                .collect(Collectors.toList());
    }


    @Override
    public void affecterPromotionAArticle(long idArticle, long idPromo) {
        Article article = articleRepo.findById(idArticle).get();
        Promotion promotion = promoRepo.findById(idPromo).get();
        article.getPromotions().add(promotion);
        articleRepo.save(article);
    }

    @Override
    public void desaffecterPromotionAArticle(long idArticle, long idPromo) {
        Article article = articleRepo.findById(idArticle).get();
        Promotion promotion = promoRepo.findById(idPromo).get();
        article.getPromotions().remove(promotion);
        articleRepo.save(article);
    }



    @Override
    public void ajouterPromotionEtAffecterAArticle(Promotion p, Long idArticle){
        Article article = articleRepo.findById(idArticle).get();
        //article parent et promo child
        article.getPromotions().add(p);
        articleRepo.save(article);
    }




}