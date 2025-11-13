package tn.esprit._4twin6.services.commande;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit._4twin6.dto.CommandeDTO.CommandeRequest;
import tn.esprit._4twin6.dto.CommandeDTO.CommandeResponse;
import tn.esprit._4twin6.entities.Commande;
import tn.esprit._4twin6.mapper.ICommandeMapper;
import tn.esprit._4twin6.repositories.CommandeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommandeService implements ICommandeServiceImpl {

    private final CommandeRepository commandeRepo;
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
}
