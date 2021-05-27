package lk.kavi.assignment.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {

    private Long item_code;
    private String item_description;
    private Integer carton_price;
    private Integer item_que;
    private Integer carton_discount;
    private Integer unit_discount;
    private Integer unit_fr_carton;

    ItemDTO(){

    }
}
