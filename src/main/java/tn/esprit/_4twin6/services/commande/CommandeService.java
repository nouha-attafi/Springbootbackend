package tn.esprit._4twin6.services.commande;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tn.esprit._4twin6.dto.CommandeDTO.CommandeRequest;
import tn.esprit._4twin6.dto.CommandeDTO.CommandeResponse;
import tn.esprit._4twin6.entities.Commande;
import tn.esprit._4twin6.enums.StatusCommande;
import tn.esprit._4twin6.mapper.ICommandeMapper;
import tn.esprit._4twin6.repositories.CommandeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommandeService implements ICommandeServiceImpl {

    private final CommandeRepository commandeRepo;

    @Qualifier("ICommandeMapperImpl")
    private final ICommandeMapper commandeMapper;

    @Override
    public CommandeResponse addCommande(CommandeRequest request) {
        Commande commande = commandeMapper.toEntity(request);
        Commande saved = commandeRepo.save(commande);
        return commandeMapper.toResponse(saved);
    }

    @Override
    public List<CommandeResponse> saveCommandes(List<CommandeRequest> requests) {
        List<Commande> commandes = requests.stream()
                .map(commandeMapper::toEntity)
                .collect(Collectors.toList());
        return commandeRepo.saveAll(commandes).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CommandeResponse selectCommandeById(long id) {
        Optional<Commande> commande = commandeRepo.findById(id);
        return commande.map(commandeMapper::toResponse)
                .orElse(null);
    }

    @Override
    public List<CommandeResponse> selectAllCommandes() {
        return commandeRepo.findAll().stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCommandeById(long id) {
        commandeRepo.deleteById(id);
    }

    @Override
    public void deleteAllCommandes() {
        commandeRepo.deleteAll();
    }

    @Override
    public long countingCommandes() {
        return commandeRepo.count();
    }

    @Override
    public boolean verifCommandeById(long id) {
        return commandeRepo.existsById(id);
    }

    // =============================
    //        JPQL QUERY METHODS
    // =============================

    @Override
    public List<CommandeResponse> getCommandesByDateCommande(LocalDate date) {
        return commandeRepo.findByDateCommande(date).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponse> getCommandesByTotalCommande(float total) {
        return commandeRepo.findByTotalCommande(total).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public long countCommandesByDateCommande(LocalDate date) {
        return commandeRepo.countByDateCommande(date);
    }

    @Override
    public void deleteCommandesByDateCommande(LocalDate date) {
        commandeRepo.deleteByDateCommande(date);
    }

    @Override
    public List<CommandeResponse> getCommandesByDateCommandeAndTotalCommande(LocalDate date, float total) {
        return commandeRepo.findByDateCommandeAndTotalCommande(date, total).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponse> getCommandesByStatusCommandeAndClientNomContainingIgnoreCase(StatusCommande status, String mot) {
        return commandeRepo.findByStatusCommandeAndClientNomContainingIgnoreCase(status, mot).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponse> getCommandesByStatusCommandeIn(List<StatusCommande> statuses) {
        return commandeRepo.findByStatusCommandeIn(statuses).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponse> getCommandesByTotalCommandeBetween(float min, float max) {
        return commandeRepo.findByTotalCommandeBetween(min, max).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponse> getCommandesByTotalCommandeGreaterThan(float total) {
        return commandeRepo.findByTotalCommandeGreaterThan(total).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponse> getCommandesByTotalCommandeGreaterThanEqual(float total) {
        return commandeRepo.findByTotalCommandeGreaterThanEqual(total).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponse> getCommandesByTotalCommandeLessThan(float total) {
        return commandeRepo.findByTotalCommandeLessThan(total).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponse> getCommandesByTotalCommandeLessThanEqual(float total) {
        return commandeRepo.findByTotalCommandeLessThanEqual(total).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponse> getCommandesByClientNomStartingWithAndStatusCommandeOrderByTotalCommande(String nomStart, StatusCommande status) {
        return commandeRepo.findByClientNomStartingWithAndStatusCommandeOrderByTotalCommande(nomStart, status).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponse> getCommandesByClientPrenomStartingWith(String prenomStart) {
        return commandeRepo.findByClientPrenomStartingWith(prenomStart).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponse> getCommandesByClientNomEndingWith(String nomEnd) {
        return commandeRepo.findByClientNomEndingWith(nomEnd).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponse> getCommandesByDateCommandeIsNull() {
        return commandeRepo.findByDateCommandeIsNull().stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponse> getCommandesByStatusCommandeIsNotNull() {
        return commandeRepo.findByStatusCommandeIsNotNull().stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }


}