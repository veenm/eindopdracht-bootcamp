package nl.veenm.novi.placedOrder;

import nl.veenm.novi.employee.Employee;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;

@Entity
@Table
public class PlacedOrder{

    @Id
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    private Long id;
    private Long customerId;
    private LocalDate orderDate;
    private float amount;
    private String payment;
    private Integer amountOfServing;
    private boolean delivery;
    private String status;

    public PlacedOrder() {
    }

    public PlacedOrder(Long customerId, LocalDate orderDate, float amount, String payment, Integer amountOfServing, boolean delivery, String status) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.amount = amount;
        this.payment = payment;
        this.amountOfServing = amountOfServing;
        this.delivery = delivery;
        this.status = status;
    }

    public PlacedOrder(Long id, Long customerId, LocalDate orderDate, float amount, String payment, Integer amountOfServing, boolean delivery, String status) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.amount = amount;
        this.payment = payment;
        this.amountOfServing = amountOfServing;
        this.delivery = delivery;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Integer getAmountOfServing() {
        return amountOfServing;
    }

    public void setAmountOfServing(Integer amountOfServing) {
        this.amountOfServing = amountOfServing;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
