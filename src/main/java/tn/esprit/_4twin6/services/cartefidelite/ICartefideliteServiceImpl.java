package tn.esprit._4twin6.services.cartefidelite;

import tn.esprit._4twin6.dto.CarteFideliteDTO.CarteFideliteRequest;
import tn.esprit._4twin6.dto.CarteFideliteDTO.CarteFideliteResponse;
import tn.esprit._4twin6.entities.CarteFidelite;
import java.util.List;

public interface ICartefideliteServiceImpl {
    // ✅ Add one loyalty card
    CarteFideliteResponse addCarteFidelite(CarteFideliteRequest request);

    // ✅ Add multiple loyalty cards
    List<CarteFideliteResponse> saveCartesFidelite(List<CarteFideliteRequest> requests);

    // ✅ Get one loyalty card by ID
    CarteFideliteResponse selectCarteFideliteById(long id);

    // ✅ Get all loyalty cards
    List<CarteFideliteResponse> selectAllCartesFidelite();

    // ✅ Delete one loyalty card by ID
    void deleteCarteFidelite(long id);

    // ✅ Delete all loyalty cards
    void deleteAllCartesFidelite();

    // ✅ Count all loyalty cards
    long countingCartesFidelite();

    // ✅ Check if a loyalty card exists by ID
    boolean verifCarteFideliteById(long id);
}
