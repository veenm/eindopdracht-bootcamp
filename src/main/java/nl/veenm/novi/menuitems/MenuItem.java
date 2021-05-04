package nl.veenm.novi.menuitems;

import javax.persistence.*;

@Entity
@Table
public class MenuItem {

    @Id
    @SequenceGenerator(name = "menuItem_sequence", sequenceName = "menuItem_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ("menuitem_sequence"))
    private Long id;
    private String name;
    private String description;
    private Double price;


    public MenuItem(Long id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public MenuItem(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public MenuItem() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
