package tn.esprit._4twin6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit._4twin6.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
