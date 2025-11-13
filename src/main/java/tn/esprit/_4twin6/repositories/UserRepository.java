package tn.esprit._4twin6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit._4twin6.entities.Adresse;
import tn.esprit._4twin6.entities.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByNom(String n );
    List<User> getByNom(String nom);
    List<User> searchByNom(String nom);
    List<User> readByNom(String nom);
    List<User> queryByNom(String nom);


    List<User> fingByNomOrPrenom(String nom, String prenom);


    List<User> findByNomLike(String nom);
    List<User> findByNomContaining(String nom);
    List<User> findByNomContains(String nom);
    List<User> findByNomIsContaining(String nom);



    List<User> findByNomEndsWith(String nom);
    List<User> findByNomStartsWith(String nom);
    List<User> findByNomIsEndingWith(String nom);

    List<User> findByCinBetween(long min, long max);
    List<User> findByCinGraterThanAndCinLessThen(long min, long max);


    List<User> findByGraterThan(long l);
    List<User> findByGraterThanequals(long l);
    List<User> findByLessThan(long l);
    List<User> readByDateNaissanceAfter(LocalDate date);
    List<User> readByDateNaissanceGraterThan(LocalDate date);
    List<User> readByDateNaissanceLessThan(LocalDate date);
    List<User> readByDateNaissanceBetween(LocalDate date);
    List<User> readByDateNaissanceBefore(LocalDate date);


}
