package tn.esprit._4twin6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit._4twin6.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
