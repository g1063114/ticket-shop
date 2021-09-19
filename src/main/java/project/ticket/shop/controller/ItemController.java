package project.ticket.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.ticket.shop.dto.ItemForm;
import project.ticket.shop.service.ItemService;

import javax.validation.Valid;

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
    public String saveItem(Model model, @Valid ItemForm itemForm){
        return null;
    }
}
