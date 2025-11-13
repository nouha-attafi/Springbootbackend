package tn.esprit._4twin6.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import tn.esprit._4twin6.dto.CommandeDTO.CommandeRequest;
import tn.esprit._4twin6.dto.CommandeDTO.CommandeResponse;
import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeRequest;
import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeResponse;
import tn.esprit._4twin6.entities.Commande;
import tn.esprit._4twin6.entities.Detail_Commande;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)

public interface ICommandeMapper {
    // Map CommandeRequest to Commande entity
    @Mapping(target = "client", ignore = true) // set manually in service
    @Mapping(target = "idCommande", ignore = true) // map list from DTO to entity
    Commande toEntity(CommandeRequest request);

    // Map Commande entity to CommandeResponce DTO
    CommandeResponse toResponse(Commande commande);

    // Map DetailCommandeRequest to DetailCommande entity
    Detail_Commande toDetailEntity(DetailCommandeRequest request);

    // Map DetailCommande entity to DetailCommandeResponce DTO
    DetailCommandeResponse toDetailResponse(Detail_Commande detailCommande);

    // Map lists
    @IterableMapping(elementTargetType = Detail_Commande.class)
    List<Detail_Commande> toDetailEntities(List<DetailCommandeRequest> requests);

    @IterableMapping(elementTargetType = DetailCommandeResponse.class)
    List<DetailCommandeResponse> toDetailResponses(List<Detail_Commande> details);
}
