package tn.esprit._4twin6.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import tn.esprit._4twin6.dto.ClientDTO.ClientRequest;
import tn.esprit._4twin6.dto.ClientDTO.ClientResponse;
import tn.esprit._4twin6.entities.Client;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)

public interface IClientMapper {
    @Mapping(target = "idClient", ignore = true)
    @Mapping(target = "adresse", ignore = true)
    @Mapping(target = "commandes", ignore = true)
    Client toEntity(ClientRequest request);
    ClientResponse toResponse(Client client);
}
