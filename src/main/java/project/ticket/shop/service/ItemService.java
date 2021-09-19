package project.ticket.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.ticket.shop.entity.item.Item;
import project.ticket.shop.repository.ItemRepository;

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
}
