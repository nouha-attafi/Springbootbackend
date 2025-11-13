package tn.esprit._4twin6.dto.AdresseDTO;
import lombok.* ;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdresseRequest {
    private String rue;
    private String ville;
    private int codePostal;

}
