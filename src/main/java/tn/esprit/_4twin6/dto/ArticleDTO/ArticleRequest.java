package tn.esprit._4twin6.dto.ArticleDTO;
import lombok.*;
import tn.esprit._4twin6.enums.TypeArticle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRequest {
    private String nomArticle;
    private Double prixArticle;
    private TypeArticle typeArticle;

}
