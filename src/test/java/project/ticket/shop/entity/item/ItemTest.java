package project.ticket.shop.entity.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.ticket.shop.service.ItemService;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemTest {

    @Autowired
    EntityManager em;

    @Autowired
    ItemService itemService;

    @Test
    public void saveItem(){
        // given
        Movie movie = new Movie();
        movie.setName("샹치와 텐 링즈의 전설");
        movie.setPrice(13000);
        movie.setStock(160);
        movie.setGenre("액션");
        movie.setRunningTime(132);

        Snack snack = new Snack();
        snack.setName("콜라");
        snack.setPrice(2500);
        snack.setStock(200);
        snack.setCategory("음료");

        // when
        Long movieId = itemService.saveItem(movie);
        Long snackId = itemService.saveItem(snack);

        // then
        assertThat(movie.getName()).isEqualTo("샹치와 텐 링즈의 전설");
        assertThat(snack.getName()).isEqualTo("콜라");
    }
}