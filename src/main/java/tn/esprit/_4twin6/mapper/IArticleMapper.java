package tn.esprit._4twin6.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit._4twin6.dto.ArticleDTO.ArticleRequest;
import tn.esprit._4twin6.dto.ArticleDTO.ArticleResponse;
import tn.esprit._4twin6.entities.Article;

@Mapper(componentModel = "spring")
public interface IArticleMapper {

    ArticleResponse toResponse(Article article);

    @Mapping(target = "idArticle", ignore = true)
    @Mapping(target="detailCommandes", ignore = true)

    Article toEntity(ArticleRequest request);
}
