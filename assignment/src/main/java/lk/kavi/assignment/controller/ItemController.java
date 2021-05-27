package lk.kavi.assignment.controller;

import lk.kavi.assignment.dto.priceDTO;
import lk.kavi.assignment.model.Item;
import lk.kavi.assignment.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(maxAge = 4200)
@RequestMapping("/main")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/item")
    private ResponseEntity<?> getAll() {
        return new ResponseEntity<>(itemService.getItemList(), HttpStatus.OK);
    }

    @GetMapping("/item/{description}")
    private ResponseEntity<?> getDetailsByName(@PathVariable("description") String description) {
        if (description == null) {
            return new ResponseEntity<>("Don't empty description", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(itemService.itemByDescription(description), HttpStatus.OK);
    }

    @GetMapping("/item/purchase/{item_desc}/{qty}/{unitOrCatron}")
    private ResponseEntity<?> purchaseOrder(@PathVariable("item_desc") String item_desc, @PathVariable("qty") Integer qty,
                                            @PathVariable("unitOrCatron") String unitOrCatron) {

        if (item_desc == null || qty == null || unitOrCatron == null) {
            return new ResponseEntity<>("Data cannot be null", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        priceDTO returnValue = itemService.itemLastPrice(item_desc, qty, unitOrCatron);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @GetMapping("/item/getPriceList")
    private ResponseEntity<?> getPriceList() {
        return new ResponseEntity<>(itemService.getPriceList(), HttpStatus.OK);
    }
}
