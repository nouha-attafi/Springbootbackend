package tn.esprit._4twin6.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.MappingConstants;
import tn.esprit._4twin6.dto.AdresseDTO.AdresseRequest;
import tn.esprit._4twin6.dto.AdresseDTO.AdresseResponse;
import tn.esprit._4twin6.entities.Adresse;


@Mapper(componentModel = "spring")
public interface IAdresseMapper {

    // Mapping explicite pour éviter nulls (ajustez si noms de champs diffèrent)
    @Mapping(target = "idAdresse", ignore = true )
    Adresse toEntity(AdresseRequest request);

    // Mapping inverse pour la réponse
    AdresseResponse toResponse(Adresse adresse);
}