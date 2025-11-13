package tn.esprit._4twin6.services.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit._4twin6.dto.ClientDTO.ClientRequest;
import tn.esprit._4twin6.dto.ClientDTO.ClientResponse;
import tn.esprit._4twin6.entities.Client;
import tn.esprit._4twin6.mapper.IClientMapper;
import tn.esprit._4twin6.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService implements IClientServiceImpl {

    private final ClientRepository clientRepo;
    private final IClientMapper clientMapper;

    @Override
    public ClientResponse addClient(ClientRequest request) {
        Client client = clientMapper.toEntity(request);
        Client saved = clientRepo.save(client);
        return clientMapper.toResponse(saved);
    }

    @Override
    public List<ClientResponse> saveClients(List<ClientRequest> requests) {
        List<Client> clients = requests.stream()
                .map(clientMapper::toEntity)
                .collect(Collectors.toList());
        return clientRepo.saveAll(clients).stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponse selectClientById(long id) {
        Optional<Client> client = clientRepo.findById(id);
        return client.map(clientMapper::toResponse)

                .orElse(null);
    }

    @Override
    public List<ClientResponse> selectAllClients() {
        return clientRepo.findAll().stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteClientById(long id) {
        clientRepo.deleteById(id);
    }

    @Override
    public void deleteAllClients() {
        clientRepo.deleteAll();
    }

    @Override
    public long countingClients() {
        return clientRepo.count();
    }

    @Override
    public boolean verifClientById(long id) {
        return clientRepo.existsById(id);
    }
}
