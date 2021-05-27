package lk.kavi.assignment.service;

import lk.kavi.assignment.model.Item;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface ItemService {

    List<Item> getItemList();

    Item itemByDescription(String description);

    Double itemLastPrice(String item_desc, Integer qty, String unitOrCatron);

    HashMap<String, HashMap<Integer, Double>> getPriceList();
}
