package tn.esprit._4twin6.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit._4twin6.enums.TypeArticle;

import java.util.List;

@Entity
@Table(name = "Article")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idArticle;

    private String nomArticle;
    private float prixArticle;

    @Enumerated(EnumType.STRING)
    private TypeArticle typeArticle;

    @ManyToMany(cascade = CascadeType.ALL)
    List<Promotion> promotions;

    @OneToMany (mappedBy = "article")
    List<Detail_Commande>detailCommandes;

}