package lk.kavi.assignment.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class ItemPriceDTO {

    private String description;
    private HashMap<Integer, Double> priceList;
}
