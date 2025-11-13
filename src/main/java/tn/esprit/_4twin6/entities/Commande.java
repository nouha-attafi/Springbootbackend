package tn.esprit._4twin6.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit._4twin6.enums.StatusCommande;

import java.time.LocalDate;
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
@Table(name = "commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long idCommande;

   LocalDate dateCommande;

    float totalCommande;

    @Enumerated(EnumType.STRING)
     StatusCommande statusCommande;

    @OneToMany(mappedBy = "commande")
    List<Detail_Commande> detailCommandes;
    @ManyToOne
    Client client;




}
