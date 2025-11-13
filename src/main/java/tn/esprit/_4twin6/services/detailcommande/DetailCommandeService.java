package tn.esprit._4twin6.services.detailcommande;

import lombok.AllArgsConstructor;
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
    private final IDetailCommandeMapper detailMapper;

    // ✅ Add one detail commande
    @Override
    public DetailCommandeResponse addDetailCommande(DetailCommandeRequest request) {
        Detail_Commande detail = detailMapper.toEntity(request);
        Detail_Commande saved = detailRepo.save(detail);
        return detailMapper.toResponse(saved);
    }

    // ✅ Add multiple details commande
    @Override
    public List<DetailCommandeResponse> saveDetailsCommande(List<DetailCommandeRequest> requests) {
        List<Detail_Commande> details = requests.stream()
                .map(detailMapper::toEntity)
                .collect(Collectors.toList());

        return detailRepo.saveAll(details).stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ✅ Get one detail commande by ID
    @Override
    public DetailCommandeResponse selectDetailCommandeById(long id) {
        Optional<Detail_Commande> detailOpt = detailRepo.findById(id);
        return detailOpt.map(detailMapper::toResponse).orElse(null);
    }

    // ✅ Get all details commande
    @Override
    public List<DetailCommandeResponse> selectAllDetailsCommande() {
        return detailRepo.findAll().stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ✅ Delete one detail commande by ID
    @Override
    public void deleteDetailCommandeById(long id) {
        detailRepo.deleteById(id);
    }

    // ✅ Delete all details commande
    @Override
    public void deleteAllDetailsCommande() {
        detailRepo.deleteAll();
    }

    // ✅ Count total details commande
    @Override
    public long countingDetailsCommande() {
        return detailRepo.count();
    }

    // ✅ Verify if a detail commande exists
    @Override
    public boolean verifDetailCommandeById(long id) {
        return detailRepo.existsById(id);
    }
}
