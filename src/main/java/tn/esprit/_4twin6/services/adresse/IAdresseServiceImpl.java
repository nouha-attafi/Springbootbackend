package tn.esprit._4twin6.services.adresse;

import tn.esprit._4twin6.dto.AdresseDTO.AdresseRequest;
import tn.esprit._4twin6.dto.AdresseDTO.AdresseResponse;
import tn.esprit._4twin6.entities.Adresse;

import java.util.List;

public interface IAdresseServiceImpl {


        AdresseResponse addAdresse(AdresseRequest request);

        List<AdresseResponse> saveAdresses(List<AdresseRequest> requests);

        AdresseResponse selectAdresseById(long id);

        List<AdresseResponse> selectAllAdresses();

        void deleteAdresse(long id);

        void deleteAllAdresses();

        long countingAdresses();

        boolean verifAdresseById(long id);
    }
