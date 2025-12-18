package tn.esprit._4twin6.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component //pour definir qu'il sagit dun bean Spring
@Aspect // pour definir que cette clas est un aspect
@Slf4j // pour ecrire des messages de logging
public class AspectClass {

    //les methodes : Advice


    //(@..)--> le type d'advice @before @after @afterthrowing @afterReturning @arround (....)---->joinPoint
    @Before("execution(* tn.esprit._4twin6.services..*.*(..))")
     public void logMethodEntry (JoinPoint jP) {
         log.info("In method  "+jP.getSignature().getName());
     }


    @After("execution(* tn.esprit._4twin6.services..*.*(..))")
    public void logMethodEnd (JoinPoint jP) {
        log.info("In method  "+jP.getSignature().getName());
    }


    @Before("execution(* tn.esprit._4twin6.services..Client*.affecter*(..))")
    public void logMethodAffect (JoinPoint jP) {
        log.info("je suis entrain d'exécution une méthode d'affectation nomeé  "+jP.getSignature().getName());
    }



    @Around("execution(* tn.esprit._4twin6.services..Client*.affecter*(..))")
    public Object logMethodAffect (ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();

        long elaspedtime = System.currentTimeMillis() - start;

        log.info("method execution :" + elaspedtime + " milliseconde.");

        return obj;
    }


}
