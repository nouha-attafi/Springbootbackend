package tn.esprit._4twin6.RestControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit._4twin6.dto.ClientDTO.ClientRequest;
import tn.esprit._4twin6.dto.ClientDTO.ClientResponse;
import tn.esprit._4twin6.entities.Client;
import tn.esprit._4twin6.services.client.IClientServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Client Management", description = "Manage cafe customers and their profiles")
@RequestMapping("clients")
public class ClientRestController {

    private IClientServiceImpl clientService;

    // ✅ Get all clients
    @GetMapping
    public List<ClientResponse> selectAllClients() {
        return clientService.selectAllClients();
    }

    // ✅ Get client by ID
    @GetMapping("/{id}")
    public ClientResponse selectClientById(@PathVariable long id) {
        return clientService.selectClientById(id);
    }

    // ✅ Add one client
    @PostMapping
    public ClientResponse addClient(@RequestBody ClientRequest request) {
        return clientService.addClient(request);
    }

    // ✅ Add multiple clients
    @PostMapping("/batch")
    public List<ClientResponse> saveClients(@RequestBody List<ClientRequest> requests) {
        return clientService.saveClients(requests);
    }

    // ✅ Delete client by ID
    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable long id) {
        clientService.deleteClientById(id);
    }

    // ✅ Delete all clients
    @DeleteMapping
    public void deleteAllClients() {
        clientService.deleteAllClients();
    }

    // ✅ Count all clients
    @GetMapping("/count")
    public long countClients() {
        return clientService.countingClients();
    }

    // ✅ Check if client exists by ID
    @GetMapping("/exists/{id}")
    public boolean verifClientById(@PathVariable long id) {
        return clientService.verifClientById(id);
    }
}
