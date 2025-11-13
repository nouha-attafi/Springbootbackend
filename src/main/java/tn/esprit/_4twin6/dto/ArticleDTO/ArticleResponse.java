package tn.esprit._4twin6.dto.ArticleDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleResponse {
    private Long idArticle;

    private String nomArticle;
    private Double prixArticle;
    private String typeArticle;
}
