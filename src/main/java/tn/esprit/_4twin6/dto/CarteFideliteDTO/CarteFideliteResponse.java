package tn.esprit._4twin6.dto.CarteFideliteDTO;
import lombok.*;
import tn.esprit._4twin6.dto.ClientDTO.ClientResponse;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarteFideliteResponse {

    private Long idCarteFidelite;
    private Integer pointsAcumules;
    private LocalDate dateCreation;
    private ClientResponse client;
}

