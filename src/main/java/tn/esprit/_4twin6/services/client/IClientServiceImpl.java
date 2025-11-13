package tn.esprit._4twin6.services.client;

import tn.esprit._4twin6.dto.ClientDTO.ClientRequest;
import tn.esprit._4twin6.dto.ClientDTO.ClientResponse;
import tn.esprit._4twin6.entities.Client;
import java.util.List;

public interface IClientServiceImpl {


    ClientResponse addClient(ClientRequest request);

    List<ClientResponse> saveClients(List<ClientRequest> requests);

    ClientResponse selectClientById(long id);

    List<ClientResponse> selectAllClients();

    void deleteClientById(long id);

    void deleteAllClients();

    long countingClients();

    boolean verifClientById(long id);
}
