package tn.esprit._4twin6.entities;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Table (name = "Detail_Commande")
public class Detail_Commande {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
   Long idDetailCommande;
    Integer quantiteArticle;
    Double sousTotalDetailArticle;
    Double sousTotalDetailArticleApresPromo;

    @ManyToOne
    Commande commande;
    @ManyToOne
    Article article;

}