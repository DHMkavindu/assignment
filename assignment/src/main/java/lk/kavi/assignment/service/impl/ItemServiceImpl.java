package lk.kavi.assignment.service.impl;

import lk.kavi.assignment.dto.ItemPriceDTO;
import lk.kavi.assignment.model.Item;
import lk.kavi.assignment.repository.ItemRepository;
import lk.kavi.assignment.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getItemList() {
        List<Item> returnList = itemRepository.findAll();
        return returnList;
    }

    @Override
    public Item itemByDescription(String description) {
        Item item = itemRepository.itemByDescription(description);
        return item;
    }

    @Override
    public Double itemLastPrice(String item_desc, Integer qty, String unitOrCatron) {
        Double returnValue = ItemPriceCalculation(item_desc, qty, unitOrCatron);
        return returnValue;
    }

    @Override
    public List<ItemPriceDTO> getPriceList() {
        List<ItemPriceDTO> itemPriceDTO = new ArrayList<>();
        List<Item> itemList = itemRepository.findAll();

        for (int i = 0; i < itemList.size(); i++) {
            HashMap<Integer, Double> priceList = new HashMap<>();
            ItemPriceDTO priceDTO = new ItemPriceDTO();
            double unitPrice = 0;
            unitPrice = Double.valueOf((itemList.get(i).getCarton_price() * 30 / 100) + itemList.get(i).getCarton_price()) / itemList.get(i).getUnit_fr_carton();
            for (int j = 1; j <= 5; j++) {
                priceList.put(j, unitPrice * j);
            }
            priceDTO.setDescription(itemList.get(i).getItem_description());
            priceDTO.setPriceList(priceList);
            itemPriceDTO.add(priceDTO);

        }
        return itemPriceDTO;
    }

    //    Calculation Unit price and Carton price  : Return final value for order que...(Double)
    private Double ItemPriceCalculation(String item_desc, Integer qty, String unitOrCatron) {
        Double unitPrice = 0.0;
        Double finalPrice = 0.0;
        if (item_desc == null || qty == null) {
            return null;
        }
        Item item = itemRepository.itemByDescription(item_desc);
        if (item == null) {
            return null;
        }
        if (qty != null) {
            if (unitOrCatron.equals("U")) {
                unitPrice = Double.valueOf((item.getCarton_price() * 30 / 100) + item.getCarton_price()) / item.getUnit_fr_carton();
                if (qty < item.getUnit_fr_carton()) {
                    finalPrice = unitPrice * qty;
                } else {
                    Integer cartonCount = qty / item.getUnit_fr_carton();
                    Integer onlyUnitCount = qty - (cartonCount * item.getUnit_fr_carton());

                    finalPrice = Double.valueOf(cartonCount * item.getCarton_price());
                    finalPrice = finalPrice + (onlyUnitCount * unitPrice);
                }
            } else if (unitOrCatron.equals("C")) {
                if (qty >= 3) {
                    finalPrice = Double.valueOf((item.getCarton_price() - (item.getCarton_price() * 10 / 100)) * qty);
                } else {
                    finalPrice = Double.valueOf(item.getCarton_price() * qty);
                }
            }
        }
        return finalPrice;
    }
}
