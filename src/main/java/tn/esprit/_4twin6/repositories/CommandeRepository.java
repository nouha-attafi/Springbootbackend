package tn.esprit._4twin6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit._4twin6.entities.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
