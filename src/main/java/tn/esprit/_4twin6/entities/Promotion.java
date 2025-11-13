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
@Table(name = "Promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long idPromotion;

     String poucentagePromotion;

     LocalDate dateDebutPromotion;

  LocalDate dateFin;

    @ManyToMany(mappedBy = "promotions")
    List<Article> articles;
}
