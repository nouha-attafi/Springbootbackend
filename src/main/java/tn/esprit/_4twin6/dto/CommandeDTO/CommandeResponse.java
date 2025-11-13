package tn.esprit._4twin6.dto.CommandeDTO;

import lombok.*;
import tn.esprit._4twin6.dto.ClientDTO.ClientResponse;
import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeRequest;
import tn.esprit._4twin6.enums.StatusCommande;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeResponse {
    private Long idCommande;
    private LocalDate dateCommande;
    private StatusCommande statusCommande;
    private float totalCommande;
    private ClientResponse client; // avoid full client recursion
    private List<DetailCommandeRequest> detailsCommande;
}
