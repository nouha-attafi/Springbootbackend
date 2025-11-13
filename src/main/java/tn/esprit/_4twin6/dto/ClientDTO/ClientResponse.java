package tn.esprit._4twin6.dto.ClientDTO;

import java.time.LocalDate;
import java.util.List;

import lombok.*;
import tn.esprit._4twin6.dto.AdresseDTO.AdresseResponse;
import tn.esprit._4twin6.dto.CarteFideliteDTO.CarteFideliteResponse;
import tn.esprit._4twin6.dto.CommandeDTO.CommandeResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponse {
    private Long idClient;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    private AdresseResponse adresse;
    private CarteFideliteResponse carteFidelite;
    private List<CommandeResponse> commandes;

}
