package tn.esprit._4twin6.services.cartefidelite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tn.esprit._4twin6.dto.CarteFideliteDTO.CarteFideliteRequest;
import tn.esprit._4twin6.dto.CarteFideliteDTO.CarteFideliteResponse;
import tn.esprit._4twin6.entities.CarteFidelite;
import tn.esprit._4twin6.mapper.ICarteFideliteMapper;
import tn.esprit._4twin6.repositories.CarteFideliteRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarteFideliteService implements ICartefideliteServiceImpl {

    private final CarteFideliteRepository carteRepo;
    private final ICarteFideliteMapper carteMapper;

    // Constructeur unique : gère l'injection des deux champs avec qualifieur pour le mapper
    @Autowired
    public CarteFideliteService(CarteFideliteRepository carteRepo,
                                @Qualifier("ICarteFideliteMapperImpl") ICarteFideliteMapper carteMapper) {
        this.carteRepo = carteRepo;
        this.carteMapper = carteMapper;
    }

    // ✅ Add one CarteFidelite (DTO-based)
    @Override
    public CarteFideliteResponse addCarteFidelite(CarteFideliteRequest request) {
        CarteFidelite carte = carteMapper.toEntity(request);
        CarteFidelite saved = carteRepo.save(carte);
        return carteMapper.toResponse(saved);
    }

    // ✅ Add multiple CarteFidelite (DTO-based)
    @Override
    public List<CarteFideliteResponse> saveCartesFidelite(List<CarteFideliteRequest> requests) {
        List<CarteFidelite> cartes = requests.stream()
                .map(carteMapper::toEntity)
                .collect(Collectors.toList());
        return carteRepo.saveAll(cartes).stream()
                .map(carteMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ✅ Get CarteFidelite by ID
    @Override
    public CarteFideliteResponse selectCarteFideliteById(long id) {
        return carteRepo.findById(id)
                .map(carteMapper::toResponse)
                .orElseGet(() -> CarteFideliteResponse.builder()
                        .idCarteFidelite(0L)
                        .pointsAcumules(0)
                     
                        .build());
    }

    // ✅ Get all CarteFidelite
    @Override
    public List<CarteFideliteResponse> selectAllCartesFidelite() {
        return carteRepo.findAll().stream()
                .map(carteMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ✅ Delete one CarteFidelite by ID
    @Override
    public void deleteCarteFidelite(long id) {
        carteRepo.deleteById(id);
    }

    // ✅ Delete all CarteFidelite
    @Override
    public void deleteAllCartesFidelite() {
        carteRepo.deleteAll();
    }

    // ✅ Count CarteFidelite
    @Override
    public long countingCartesFidelite() {
        return carteRepo.count();
    }

    // ✅ Verify existence by ID
    @Override
    public boolean verifCarteFideliteById(long id) {
        return carteRepo.existsById(id);
    }
}