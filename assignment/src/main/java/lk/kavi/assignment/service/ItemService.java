package lk.kavi.assignment.service;

import lk.kavi.assignment.dto.ItemPriceDTO;
import lk.kavi.assignment.dto.priceDTO;
import lk.kavi.assignment.model.Item;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface ItemService {

    List<Item> getItemList();

    Item itemByDescription(String description);

    priceDTO itemLastPrice(String item_desc, Integer qty, String unitOrCatron);

    List<ItemPriceDTO> getPriceList();
}
