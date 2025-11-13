package tn.esprit._4twin6.services.adresse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tn.esprit._4twin6.dto.AdresseDTO.AdresseRequest;
import tn.esprit._4twin6.dto.AdresseDTO.AdresseResponse;
import tn.esprit._4twin6.entities.Adresse;
import tn.esprit._4twin6.mapper.IAdresseMapper;
import tn.esprit._4twin6.repositories.AdresseRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdresseService implements IAdresseServiceImpl {

    private final AdresseRepository adresseRepo;
    private final IAdresseMapper adresseMapper;

    // Constructeur unique : gère l'injection des deux champs avec qualifieur pour le mapper
    @Autowired
    public AdresseService(AdresseRepository adresseRepo,
                          @Qualifier("IAdresseMapperImpl") IAdresseMapper adresseMapper) {
        this.adresseRepo = adresseRepo;
        this.adresseMapper = adresseMapper;
    }

    @Override
    public AdresseResponse addAdresse(AdresseRequest request) {
        Adresse adresse = adresseMapper.toEntity(request);
        Adresse saved = adresseRepo.save(adresse);
        return adresseMapper.toResponse(saved);
    }

    @Override
    public List<AdresseResponse> saveAdresses(List<AdresseRequest> requests) {
        List<Adresse> adresses = requests.stream()
                .map(adresseMapper::toEntity)
                .collect(Collectors.toList());
        List<Adresse> saved = adresseRepo.saveAll(adresses);
        return saved.stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AdresseResponse selectAdresseById(long id) {
        return adresseRepo.findById(id)
                .map(adresseMapper::toResponse)
                .orElseGet(() -> AdresseResponse.builder()
                        .idAdresse(null)
                        .rue("default rue")
                        .ville("default ville")
                        .build());
    }

    @Override
    public List<AdresseResponse> selectAllAdresses() {
        return adresseRepo.findAll().stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdresse(long id) {
        adresseRepo.deleteById(id);
    }

    @Override
    public void deleteAllAdresses() {
        adresseRepo.deleteAll();
    }

    @Override
    public long countingAdresses() {
        return adresseRepo.count();
    }

    @Override
    public boolean verifAdresseById(long id) {
        return adresseRepo.existsById(id);
    }
}