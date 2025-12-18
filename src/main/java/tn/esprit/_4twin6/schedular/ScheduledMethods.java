package tn.esprit._4twin6.schedular;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit._4twin6.dto.ArticleDTO.ArticleResponse;
import tn.esprit._4twin6.entities.Client;
import tn.esprit._4twin6.services.article.IArticleService;
import tn.esprit._4twin6.services.client.IClientServiceImpl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class ScheduledMethods {
    IClientServiceImpl clientService;

    private final IArticleService articleService;
    @Scheduled(fixedRate = 2000)
    public void methode(){
        log.info("methode");

    }


    @Scheduled(fixedDelay = 2000)
    public void methode2(){
        log.info("methodeDelay");

    }


    @Scheduled(cron = "0 0 */6 * * *")
    public void methode3(){
        log.info("methodeCron");

    }

    @Scheduled(cron = "0 0 0 * * *")
    public void method4() {
        for (Client client : clientService.incrementerPts()){
            log.info("CLient : "+ client.getPrenom() + " "+ client.getNom());}}









    @Scheduled(cron = "0 0 0 1 * *") // Le 1er de chaque mois à 00:00:00
    public void logMonthlyPromotedArticles() {
        YearMonth currentMonth = YearMonth.now();
        List<ArticleResponse> promotedArticles = articleService.findPromotedArticlesForMonth(currentMonth);

        if (promotedArticles.isEmpty()) {
            log.info("Aucune promotion active pour le mois de {}.", currentMonth);
        } else {
            String articlesList = promotedArticles.stream()
                    .map(ArticleResponse::getNomArticle)
                    .collect(Collectors.joining(", "));
            log.info("Articles en promotion pour le mois de {} : {}", currentMonth, articlesList);
        }
    }



}
