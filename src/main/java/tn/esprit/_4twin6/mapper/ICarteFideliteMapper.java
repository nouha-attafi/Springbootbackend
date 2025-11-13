package tn.esprit._4twin6.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import tn.esprit._4twin6.dto.CarteFideliteDTO.CarteFideliteRequest;
import tn.esprit._4twin6.dto.CarteFideliteDTO.CarteFideliteResponse;
import tn.esprit._4twin6.entities.CarteFidelite;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICarteFideliteMapper {

    // Mapping explicite : source = champ DTO, target = champ entité
    @Mapping(target = "idCarteFidelite", ignore = true)// Ajustez si orthographe différente (ex. pointsAcumules -> pointsAcumules)
    @Mapping(target = "client", ignore = true)// Ajustez si orthographe différente (ex. pointsAcumules -> pointsAcumules)
    CarteFidelite toEntity(CarteFideliteRequest request);

    // Mapping inverse (entité -> Response) : ajoutez si besoin pour la réponse

    CarteFideliteResponse toResponse(CarteFidelite carteFidelite);
}