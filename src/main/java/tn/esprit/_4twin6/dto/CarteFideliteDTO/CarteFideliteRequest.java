package tn.esprit._4twin6.dto.CarteFideliteDTO;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarteFideliteRequest {
    private Integer pointsAcumules;
    private LocalDate dateCreation;
    private Long clientId;
}
