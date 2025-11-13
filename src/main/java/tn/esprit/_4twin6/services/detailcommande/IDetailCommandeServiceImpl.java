package tn.esprit._4twin6.services.detailcommande;

import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeRequest;
import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeResponse;
import tn.esprit._4twin6.entities.Detail_Commande;
import java.util.List;

public interface IDetailCommandeServiceImpl {


    // ✅ Add one detail commande (from DTO)
    DetailCommandeResponse addDetailCommande(DetailCommandeRequest request);

    // ✅ Add multiple details commande
    List<DetailCommandeResponse> saveDetailsCommande(List<DetailCommandeRequest> requests);

    // ✅ Get one detail commande by ID
    DetailCommandeResponse selectDetailCommandeById(long id);

    // ✅ Get all details commande
    List<DetailCommandeResponse> selectAllDetailsCommande();

    // ✅ Delete one detail commande by ID
    void deleteDetailCommandeById(long id);

    // ✅ Delete all details commande
    void deleteAllDetailsCommande();

    // ✅ Count total details commande
    long countingDetailsCommande();

    // ✅ Verify if a detail commande exists
    boolean verifDetailCommandeById(long id);
}