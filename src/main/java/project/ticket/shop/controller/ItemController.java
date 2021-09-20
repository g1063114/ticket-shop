package project.ticket.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.ticket.shop.dto.ItemForm;
import project.ticket.shop.entity.Member;
import project.ticket.shop.entity.item.Item;
import project.ticket.shop.entity.item.Movie;
import project.ticket.shop.entity.item.Snack;
import project.ticket.shop.service.ItemService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createItemForm(Model model){
        model.addAttribute("itemForm", new ItemForm());
        return "item/createItemForm";
    }

    @PostMapping("/items/new")
    public String saveItem(Model model, @Valid ItemForm itemForm, BindingResult result){
        if(result.hasErrors()){
            return "members/createItemForm";
        }

        if( itemForm.getDis().equals("M") ){
            Movie movie = new Movie();
            movie.setName(itemForm.getName());
            movie.setGenre(itemForm.getGenre());
            movie.setRunningTime(itemForm.getRunningTime());
            movie.setPrice(itemForm.getPrice());
            movie.setStock(itemForm.getStock());

            itemService.saveItem(movie);
        } else{
            Snack snack = new Snack();
            snack.setName(itemForm.getName());
            snack.setPrice(itemForm.getPrice());
            snack.setStock(itemForm.getStock());
            snack.setCategory(itemForm.getCategory());

            itemService.saveItem(snack);
        }
        return "redirect:/";
    }

    @GetMapping("/items")
    public String itemList(Model model){
        List<Item> items = itemService.itemList();
        model.addAttribute("items",items);

        return "item/itemList";
    }
}
