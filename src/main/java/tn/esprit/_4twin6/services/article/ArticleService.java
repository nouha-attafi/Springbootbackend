    package tn.esprit._4twin6.services.article;

    import lombok.AllArgsConstructor;
    import org.springframework.stereotype.Service;
    import tn.esprit._4twin6.dto.ArticleDTO.ArticleRequest;
    import tn.esprit._4twin6.dto.ArticleDTO.ArticleResponse;
    import tn.esprit._4twin6.entities.Article;
    import tn.esprit._4twin6.mapper.IArticleMapper;
    import tn.esprit._4twin6.repositories.ArticleRepository;

    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    @AllArgsConstructor
    public class ArticleService implements IArticleServiceImpl {

        private final ArticleRepository articleRepo;
        private final IArticleMapper articleMapper;

        @Override
        public ArticleResponse addArticle(ArticleRequest request) {
            Article article = articleMapper.toEntity(request);
            Article saved = articleRepo.save(article);
            return articleMapper.toResponse(saved);
        }

        @Override
        public List<ArticleResponse> saveArticles(List<ArticleRequest> requests) {
            List<Article> articles = requests.stream()
                    .map(articleMapper::toEntity)
                    .collect(Collectors.toList());

            List<Article> saved = articleRepo.saveAll(articles);
            return saved.stream()
                    .map(articleMapper::toResponse)
                    .collect(Collectors.toList());
        }

        @Override
        public ArticleResponse selectArticleById(long id) {
            return articleRepo.findById(id)
                    .map(articleMapper::toResponse)
                    .orElseGet(() -> ArticleResponse.builder()

                            .nomArticle("default nom")
                            .prixArticle(0.0)
                            .build());
        }

        @Override
        public List<ArticleResponse> selectAllArticles() {
            return articleRepo.findAll().stream()
                    .map(articleMapper::toResponse)
                    .collect(Collectors.toList());
        }

        @Override
        public void deleteArticle(long id) {
            articleRepo.deleteById(id);
        }

        @Override
        public void deleteAllArticles() {
            articleRepo.deleteAll();
        }

        @Override
        public long countingArticles() {
            return articleRepo.count();
        }

        @Override
        public boolean verifArticleById(long id) {
            return articleRepo.existsById(id);
        }
    }