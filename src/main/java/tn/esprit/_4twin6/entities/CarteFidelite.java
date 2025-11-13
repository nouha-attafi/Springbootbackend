package tn.esprit._4twin6.entities;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
@Table (name = "CarteFidelite")
public class CarteFidelite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idCarteFidelite;
    Integer pointsAccumules;
    LocalDate dateCreation;
    @OneToOne
    Client client;

}