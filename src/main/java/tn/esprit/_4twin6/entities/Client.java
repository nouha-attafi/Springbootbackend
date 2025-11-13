package tn.esprit._4twin6.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
   Long idClient;

     String nom;

     String prenom;

     LocalDate dateNaissence;

    @OneToOne
    Adresse adresse;
    @OneToOne(mappedBy = "client")
    CarteFidelite carteFidelite;
    @OneToMany(mappedBy = "client")
    List<Commande> commandes;

}
