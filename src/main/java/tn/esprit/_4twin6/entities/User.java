package tn.esprit._4twin6.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id //Auto_increment
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    long id;
    @Column(name="firstName")
    String nom; //first_name
    @Column(name = "lastName")
    String prenom; //last_name
    @Transient
    int age; // Pas de correspondant dans le BD
    @Temporal(TemporalType.DATE)
    Date dateAjout;
    LocalDate dateNaissance;
    @Column(unique = true)
    long cin; // unique

}