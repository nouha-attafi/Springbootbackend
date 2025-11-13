package tn.esprit._4twin6.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table (name = "Adresse")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode


public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long idAdresse;

     String rue;
     String ville;
     int codePostal;




    @OneToOne
    @JoinColumn(name = "id_client")
    private Client client;

}
