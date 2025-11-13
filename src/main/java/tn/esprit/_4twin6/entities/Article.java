package tn.esprit._4twin6.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit._4twin6.enums.TypeArticle;

import java.util.List;


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
@Table (name = "Article")
public class Article {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
     long idArticle;
     String nomArticle;
    Double prixArticle;
    @Enumerated(EnumType.STRING)
     TypeArticle typeArticle;

    @OneToMany(mappedBy = "article")
    List<Detail_Commande> detailCommandes;
    @ManyToMany
    List<Promotion> promotions;
}
