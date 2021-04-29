package nl.veenm.novi.delivery;

import javax.persistence.*;

@Entity
@Table
public class Delivery {

    @Id
    @SequenceGenerator(name = "delivery_sequence", sequenceName = "delivery_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_sequence")
    private Long id;
    private Long orderId;
    private Long customerId;
    private String customerAddress;
    private String customerCity;
    private String customerPhone;
    private boolean paid;
    private float amount;

    public Delivery() {
    }

    public Delivery(Long orderId, Long customerId, String customerAddress, String customerCity, String customerPhone, boolean paid, float amount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerPhone = customerPhone;
        this.paid = paid;
        this.amount = amount;
    }

    public Delivery(Long id, Long orderId, Long customerId, String customerAddress, String customerCity, String customerPhone, boolean paid, float amount) {
        this.id = id;
        this.orderId = orderId;
        this.customerAddress = customerAddress;
        this.customerId = customerId;
        this.customerCity = customerCity;
        this.customerPhone = customerPhone;
        this.paid = paid;
        this.amount = amount;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customer_address) {
        this.customerAddress = customer_address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long order_id) {
        this.orderId = order_id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customer_id) {
        this.customerId = customer_id;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customer_city) {
        this.customerCity = customer_city;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customer_phone) {
        this.customerPhone = customer_phone;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", order_id=" + orderId +
                ", customer_id=" + customerId +
                ", customer_address='" + customerAddress + '\'' +
                ", customer_city='" + customerCity + '\'' +
                ", customer_phone='" + customerPhone + '\'' +
                ", paid=" + paid +
                ", amount=" + amount +
                '}';
    }
}
