package nl.veenm.novi.placedOrderDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class PlacedOrderDetails {

    @Id
    @SequenceGenerator(name = "orderDetail_sequence", sequenceName = "orderDetail_sequence", allocationSize = 1)
    private Long id;
    private Long placedOrderId;
    private Long itemId;
    private Integer quantity;

    public PlacedOrderDetails() {
    }

    public PlacedOrderDetails(Long placedOrderId, Long itemId, Integer quantity) {
        this.placedOrderId = placedOrderId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public PlacedOrderDetails(Long id, Long placedOrderId, Long itemId, Integer quantity) {
        this.id = id;
        this.placedOrderId = placedOrderId;
        this.itemId = itemId;
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", placedOrderId=" + placedOrderId +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                '}';
    }
}
