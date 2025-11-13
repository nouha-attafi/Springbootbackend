package tn.esprit._4twin6.dto.DetailCommandeDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailCommandeRequest {
    private Long idDetailCommande;
    private Integer quantiteArticle;

}
