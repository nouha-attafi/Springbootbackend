package tn.esprit._4twin6.services.commande;

import tn.esprit._4twin6.dto.CommandeDTO.CommandeRequest;
import tn.esprit._4twin6.dto.CommandeDTO.CommandeResponse;
import tn.esprit._4twin6.entities.Commande;
import java.util.List;

public interface ICommandeServiceImpl {
    // ✅ Add one commande (from DTO)
    CommandeResponse addCommande(CommandeRequest request);

    // ✅ Add multiple commandes
    List<CommandeResponse> saveCommandes(List<CommandeRequest> requests);

    // ✅ Get one commande by ID
    CommandeResponse selectCommandeById(long id);

    // ✅ Get all commandes
    List<CommandeResponse> selectAllCommandes();

    // ✅ Delete one commande by ID
    void deleteCommandeById(long id);

    // ✅ Delete all commandes
    void deleteAllCommandes();

    // ✅ Count total commandes
    long countingCommandes();

    // ✅ Verify if a commande exists
    boolean verifCommandeById(long id);
}
