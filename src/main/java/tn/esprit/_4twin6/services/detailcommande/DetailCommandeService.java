package tn.esprit._4twin6.services.detailcommande;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeRequest;
import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeResponse;
import tn.esprit._4twin6.entities.Detail_Commande;
import tn.esprit._4twin6.mapper.IDetailCommandeMapper;
import tn.esprit._4twin6.repositories.Detail_CommandeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DetailCommandeService implements IDetailCommandeServiceImpl {

    private final Detail_CommandeRepository detailRepo;

    @Qualifier("IDetailCommandeMapperImpl")
    private final IDetailCommandeMapper detailMapper;

    @Override
    public DetailCommandeResponse addDetailCommande(DetailCommandeRequest request) {
        Detail_Commande detail = detailMapper.toEntity(request);
        Detail_Commande saved = detailRepo.save(detail);
        return detailMapper.toResponse(saved);
    }

    @Override
    public List<DetailCommandeResponse> saveDetailsCommande(List<DetailCommandeRequest> requests) {
        List<Detail_Commande> details = requests.stream()
                .map(detailMapper::toEntity)
                .collect(Collectors.toList());
        return detailRepo.saveAll(details).stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DetailCommandeResponse selectDetailCommandeById(long id) {
        Optional<Detail_Commande> detailOpt = detailRepo.findById(id);
        return detailOpt.map(detailMapper::toResponse).orElse(null);
    }

    @Override
    public List<DetailCommandeResponse> selectAllDetailsCommande() {
        return detailRepo.findAll().stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDetailCommandeById(long id) {
        detailRepo.deleteById(id);
    }

    @Override
    public void deleteAllDetailsCommande() {
        detailRepo.deleteAll();
    }

    @Override
    public long countingDetailsCommande() {
        return detailRepo.count();
    }

    @Override
    public boolean verifDetailCommandeById(long id) {
        return detailRepo.existsById(id);
    }

    // =============================
    //        QUERY METHODS
    // =============================

    @Override
    public List<DetailCommandeResponse> findDetailsByQuantiteArticle(Integer quantite) {
        return detailRepo.findByQuantiteArticle(quantite)
                .stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponse> findDetailsBySousTotalDetailArticle(Double sousTotal) {
        return detailRepo.findBySousTotalDetailArticle(sousTotal)
                .stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public long countDetailsByQuantiteArticleGreaterThan(Integer quantite) {
        return detailRepo.countByQuantiteArticleGreaterThan(quantite);
    }

    @Override
    public boolean existsDetailsBySousTotalDetailArticleGreaterThan(Double sousTotal) {
        return detailRepo.existsBySousTotalDetailArticleGreaterThan(sousTotal);
    }

    @Override
    public List<DetailCommandeResponse> findDetailsByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThanEqual(
            Integer minQuant, Integer maxQuant, Double minSousTotal) {
        return detailRepo.findByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThanEqual(minQuant, maxQuant, minSousTotal)
                .stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponse> findDetailsBySousTotalDetailArticleBetweenOrderByQuantiteArticleAsc(Double min, Double max) {
        return detailRepo.findBySousTotalDetailArticleBetweenOrderByQuantiteArticleAsc(min, max)
                .stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponse> findDetailsBySousTotalDetailArticleApresPromoBetween(Double min, Double max) {
        return detailRepo.findBySousTotalDetailArticleApresPromoBetween(min, max)
                .stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponse> findDetailsByQuantiteArticleOrSousTotalDetailArticleGreaterThan(
            Integer quantite, Double minSousTotal) {
        return detailRepo.findByQuantiteArticleOrSousTotalDetailArticleGreaterThan(quantite, minSousTotal)
                .stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponse> findFirst5DetailsByOrderBySousTotalDetailArticleDesc() {
        return detailRepo.findFirst5ByOrderBySousTotalDetailArticleDesc()
                .stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponse> findDetailsByQuantiteArticleIsNull() {
        return detailRepo.findByQuantiteArticleIsNull()
                .stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponse> findDetailsBySousTotalDetailArticleApresPromoIsNotNull() {
        return detailRepo.findBySousTotalDetailArticleApresPromoIsNotNull()
                .stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponse> findAllDetailsWithCommandeAndArticle() {
        return detailRepo.findAllWithCommandeAndArticle()
                .stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }
}