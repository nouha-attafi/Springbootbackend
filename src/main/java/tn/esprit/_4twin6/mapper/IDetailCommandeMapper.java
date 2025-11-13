package tn.esprit._4twin6.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeRequest;
import tn.esprit._4twin6.dto.DetailCommandeDTO.DetailCommandeResponse;
import tn.esprit._4twin6.entities.Detail_Commande;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IDetailCommandeMapper {

    // Map DetailCommandeRequest DTO to entity
    Detail_Commande toEntity(DetailCommandeRequest request);

    // Map DetailCommande entity to DetailCommandeResponce DTO
    @Mapping(source = "article.idArticle", target = "articleId") // map only article ID
    DetailCommandeResponse toResponse(Detail_Commande detailCommande);


}
