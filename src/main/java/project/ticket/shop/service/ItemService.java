package project.ticket.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.ticket.shop.entity.item.Item;
import project.ticket.shop.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /*
     * 상품 등록
     */
    @Transactional
    public Long saveItem(Item item){
        Item items = itemRepository.save(item);
        return items.getId();
    }

    /*
     * 상품 출력
     */
    public List<Item> itemList(){
        List<Item> items = itemRepository.findAll();
        return items;
    }

    /*
     * 상품 수정
     */
    public void updateItem(Long itemId, String name, int price, int stock, String genre, int runningTime, String category){
        Optional<Item> findItem = itemRepository.findById(itemId);

    }
}
