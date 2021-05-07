package nl.veenm.novi.placedOrderDetails;

import javax.persistence.*;

@Entity
@Table
public class PlacedOrderDetails{

    @Id
    @SequenceGenerator(name = "orderDetail_sequence", sequenceName = "orderDetail_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderDetail_sequence")
    private Long id;
    private Long placedOrderId;
    private Long itemId;
    private Integer quantity;

    public PlacedOrderDetails() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlacedOrderId() {
        return placedOrderId;
    }

    public void setPlacedOrderId(Long placedOrderId) {
        this.placedOrderId = placedOrderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }



}
