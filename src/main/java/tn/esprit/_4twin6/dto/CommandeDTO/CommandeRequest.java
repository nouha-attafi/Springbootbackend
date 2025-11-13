package tn.esprit._4twin6.dto.CommandeDTO;

import lombok.*;
import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeRequest;
import tn.esprit._4twin6.enums.StatusCommande;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeRequest {
    private LocalDate dateCommande;
    private Double totalCommande;
    private StatusCommande statusCommande;
    private List<DetailCommandeRequest> detailsCommande;
    private Long clientId;

}
