package lk.kavi.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long item_code;

    @Column(name = "item_description")
    private String item_description;

    @Column(name = "carton_price")
    private Integer carton_price;

    @Column(name = "item_que")
    private Integer item_que;

    @Column(name = "carton_discount")
    private Integer carton_discount;

    @Column(name = "unit_discount")
    private Integer unit_discount;

    @Column(name = "unit_fr_catron")
    private Integer unit_fr_carton;

    public Item() {
    }
}
