package tn.esprit._4twin6.services.adresse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tn.esprit._4twin6.dto.AdresseDTO.AdresseRequest;
import tn.esprit._4twin6.dto.AdresseDTO.AdresseResponse;
import tn.esprit._4twin6.entities.Adresse;
import tn.esprit._4twin6.entities.Client;
import tn.esprit._4twin6.mapper.IAdresseMapper;
import tn.esprit._4twin6.repositories.AdresseRepository;
import tn.esprit._4twin6.repositories.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdresseService implements IAdresseServiceImpl {

    private final AdresseRepository adresseRepo;

    private final IAdresseMapper adresseMapper;
    private final ClientRepository clientRepository;

    // Constructeur unique : gère l'injection des deux champs avec qualifieur pour le mapper
    @Autowired
    public AdresseService(AdresseRepository adresseRepo,
                          @Qualifier("IAdresseMapperImpl") IAdresseMapper adresseMapper, ClientRepository clientRepository) {
        this.adresseRepo = adresseRepo;
        this.adresseMapper = adresseMapper;
        this.clientRepository = clientRepository;
    }

    // Méthodes CRUD existantes
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

    // Nouvelles implémentations pour AdresseRepository
    @Override
    public List<AdresseResponse> getAdressesByVille(String ville) {
        return adresseRepo.findByVille(ville).stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> getAdressesByCodePostal(int codePostal) {
        return adresseRepo.findByCodePostal(codePostal).stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public long countAdressesByVille(String ville) {
        return adresseRepo.countByVille(ville);
    }

    @Override
    public void deleteAdressesByVille(String ville) {
        adresseRepo.deleteByVille(ville);
    }

    @Override
    public List<AdresseResponse> getAdressesByVilleAndCodePostal(String ville, int codePostal) {
        return adresseRepo.findByVilleAndCodePostal(ville, codePostal).stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> getAdressesByVilleIgnoreCaseAndRueContaining(String ville, String mot) {
        return adresseRepo.findByVilleIgnoreCaseAndRueContaining(ville, mot).stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> getAdressesByVillesIn(List<String> villes) {
        return adresseRepo.findByVilleIn(villes).stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> getAdressesByCodePostalBetween(int min, int max) {
        return adresseRepo.findByCodePostalBetween(min, max).stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> getAdressesByCodePostalGreaterThan(int codePostal) {
        return adresseRepo.findByCodePostalGreaterThan(codePostal).stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> getAdressesByCodePostalGreaterThanEqual(int codePostal) {
        return adresseRepo.findByCodePostalGreaterThanEqual(codePostal).stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> getAdressesByCodePostalLessThan(int codePostal) {
        return adresseRepo.findByCodePostalLessThan(codePostal).stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> getAdressesByCodePostalLessThanEqual(int codePostal) {
        return adresseRepo.findByCodePostalLessThanEqual(codePostal).stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> getAdressesByRueStartingWithAndVilleOrderByCodePostal(String rueStart, String ville) {
        return adresseRepo.findByRueStartingWithAndVilleOrderByCodePostal(rueStart, ville).stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> getAdressesByRueStartingWith(String rueStart) {
        return adresseRepo.findByRueStartingWith(rueStart).stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> getAdressesByVilleEndingWith(String villeEnd) {
        return adresseRepo.findByVilleEndingWith(villeEnd).stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> getAdressesByRueIsNull() {
        return adresseRepo.findByRueIsNull().stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> getAdressesByVilleIsNotNull() {
        return adresseRepo.findByVilleIsNotNull().stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void ajouterEtAffecterAdresseAClient(Adresse adresse, Client client) {
        client.setAdresse(adresse);
        clientRepository.save(client);
    }
}