package project.ticket.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.ticket.shop.entity.Member;
import project.ticket.shop.entity.item.Movie;
import project.ticket.shop.entity.item.Snack;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

        public void init(){
            Member member1 = new Member("최진호", "g1063114@naver.com", 28);
            Member member2 = new Member("테스트", "g1063114@gmail.com", 24);
            em.persist(member1);
            em.persist(member2);

            Movie movie = createMovie("샹치와 텐 링즈의 전설",13000,120,"액션",146);
            em.persist(movie);

            Snack snack = createSnack("콜라",2500,300,"음료");
            em.persist(snack);

        }
    }

    static public Movie createMovie(String name, int price, int stock, String genre, int runningTime){
        Movie movie = new Movie();
        movie.setName(name);
        movie.setPrice(price);
        movie.setStock(stock);
        movie.setGenre(genre);
        movie.setRunningTime(runningTime);
        return movie;
    }

    static public Snack createSnack(String name, int price, int stock, String category){
        Snack snack = new Snack();
        snack.setName(name);
        snack.setPrice(price);
        snack.setStock(stock);
        snack.setCategory(category);
        return snack;
    }
}
