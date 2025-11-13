package tn.esprit._4twin6.dto.PromotionDTO;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionRequest {
    private Double pourcentagePromo;
    private LocalDate dateDebutPromo;
    private LocalDate dateFinPromo;
    private List<Long> articleIds;
}
