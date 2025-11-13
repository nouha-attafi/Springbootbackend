package tn.esprit._4twin6.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import tn.esprit._4twin6.dto.PromotionDTO.PromotionRequest;
import tn.esprit._4twin6.dto.PromotionDTO.PromotionResponse;
import tn.esprit._4twin6.entities.Article;
import tn.esprit._4twin6.entities.Promotion;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IPromotionMapper {

    @Mapping(target = "articles", source = "articleIds", qualifiedByName = "mapIdsToArticles")
    Promotion toEntity(PromotionRequest request);

    @Mapping(target = "articleIds", source = "articles", qualifiedByName = "mapArticlesToIds")
    PromotionResponse toResponse(Promotion promotion);

    List<PromotionResponse> toResponseList(List<Promotion> promotions);

    @Named("mapArticlesToIds")
    default List<Long> mapArticlesToIds(List<Article> articles) {
        if (articles == null) return null;
       return articles.stream().map(Article::getIdArticle).collect(Collectors.toList());
    }
//
    @Named("mapIdsToArticles")
    default List<Article> mapIdsToArticles(List<Long> ids) {
        if (ids == null) return null;
  return ids.stream().map(id -> {
        Article a = new Article();
            a.setIdArticle(id);
            return a;
        }).collect(Collectors.toList());
   }
}
