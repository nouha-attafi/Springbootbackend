package tn.esprit._4twin6.dto.DetailCommandeDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailCommandeResponse {
    private Long idDetailCommande;
    private Integer quantiteArticle;
    private Double sousTotalDetailArticle;
    private Double sousTotalDetailArticleApresPromo;

    private Long articleId; // instead of full Article object
}


