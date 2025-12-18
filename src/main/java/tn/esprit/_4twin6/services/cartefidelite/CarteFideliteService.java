package tn.esprit._4twin6.services.cartefidelite;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tn.esprit._4twin6.dto.CarteFideliteDTO.CarteFideliteRequest;
import tn.esprit._4twin6.dto.CarteFideliteDTO.CarteFideliteResponse;
import tn.esprit._4twin6.entities.CarteFidelite;
import tn.esprit._4twin6.mapper.ICarteFideliteMapper;
import tn.esprit._4twin6.repositories.CarteFideliteRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarteFideliteService implements ICartefideliteServiceImpl {

    private final CarteFideliteRepository carteRepo;

    @Qualifier("ICarteFideliteMapperImpl")
    private final ICarteFideliteMapper carteMapper;

    @Override
    public CarteFideliteResponse addCarteFidelite(CarteFideliteRequest request) {
        CarteFidelite carte = carteMapper.toEntity(request);
        CarteFidelite saved = carteRepo.save(carte);
        return carteMapper.toResponse(saved);
    }

    @Override
    public List<CarteFideliteResponse> saveCartesFidelite(List<CarteFideliteRequest> requests) {
        List<CarteFidelite> cartes = requests.stream()
                .map(carteMapper::toEntity)
                .collect(Collectors.toList());
        List<CarteFidelite> saved = carteRepo.saveAll(cartes);
        return saved.stream()
                .map(carteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CarteFideliteResponse selectCarteFideliteById(long id) {
        return carteRepo.findById(id)
                .map(carteMapper::toResponse)
                .orElseGet(() -> CarteFideliteResponse.builder()
                        .idCarteFidelite(null)
                        .pointsAcumules(0)
                        .dateCreation(null)
                        .build());
    }

    @Override
    public List<CarteFideliteResponse> selectAllCartesFidelite() {
        return carteRepo.findAll().stream()
                .map(carteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCarteFidelite(long id) {
        carteRepo.deleteById(id);
    }

    @Override
    public void deleteAllCartesFidelite() {
        carteRepo.deleteAll();
    }

    @Override
    public long countingCartesFidelite() {
        return carteRepo.count();
    }

    @Override
    public boolean verifCarteFideliteById(long id) {
        return carteRepo.existsById(id);
    }

    // =============================
    //        QUERY METHODS
    // =============================

    @Override
    public List<CarteFideliteResponse> findCartesByPointsAcumules(Integer pointsAcumules) {
        return carteRepo.findByPointsAcumules(pointsAcumules).stream()
                .map(carteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteFideliteResponse> findCartesByDateCreation(LocalDate dateCreation) {
        return carteRepo.findByDateCreation(dateCreation).stream()
                .map(carteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public long countCartesByPointsAcumulesGreaterThan(Integer pointsAcumules) {
        return carteRepo.countByPointsAcumulesGreaterThan(pointsAcumules);
    }

    @Override
    public void deleteCartesByDateCreationBefore(LocalDate date) {
        carteRepo.deleteByDateCreationBefore(date);
    }

    @Override
    public List<CarteFideliteResponse> findCartesByPointsAcumulesBetweenAndDateCreationAfter(Integer minPoints, Integer maxPoints, LocalDate dateCreation) {
        return carteRepo.findByPointsAcumulesBetweenAndDateCreationAfter(minPoints, maxPoints, dateCreation).stream()
                .map(carteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteFideliteResponse> findCartesByPointsAcumulesGreaterThanEqualOrderByDateCreationAsc(Integer pointsAcumules) {
        return carteRepo.findByPointsAcumulesGreaterThanEqualOrderByDateCreationAsc(pointsAcumules).stream()
                .map(carteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteFideliteResponse> findCartesByDateCreationBetween(LocalDate startDate, LocalDate endDate) {
        return carteRepo.findByDateCreationBetween(startDate, endDate).stream()
                .map(carteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteFideliteResponse> findCartesByPointsAcumulesLessThanOrDateCreationBefore(Integer maxPoints, LocalDate date) {
        return carteRepo.findByPointsAcumulesLessThanOrDateCreationBefore(maxPoints, date).stream()
                .map(carteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CarteFideliteResponse> findTopCarteByPointsAcumulesDesc() {
        return carteRepo.findTopByOrderByPointsAcumulesDesc()
                .map(carteMapper::toResponse);
    }

    @Override
    public List<CarteFideliteResponse> findCartesByDateCreationIsNull() {
        return carteRepo.findByDateCreationIsNull().stream()
                .map(carteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteFideliteResponse> findCartesByPointsAcumulesIsNotNull() {
        return carteRepo.findByPointsAcumulesIsNotNull().stream()
                .map(carteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteFideliteResponse> findCartesByClientNomAndPrenom(String nom, String prenom) {
        return carteRepo.findByClientNomAndPrenom(nom, prenom).stream()
                .map(carteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteFideliteResponse> findFirst5CartesByOrderByPointsAcumulesDesc() {
        return carteRepo.findFirst5ByOrderByPointsAcumulesDesc().stream()
                .map(carteMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Nouvelle implémentation : Récupérer les points accumulés pour une carte spécifique par ID
    @Override
    public Integer getPointsAccumules(Long id) {
        return carteRepo.findById(id)
                .map(CarteFidelite::getPointsAcumules)
                .orElse(0);
    }
}