package nl.veenm.novi.placedOrder;

import nl.veenm.novi.account.Account;
import nl.veenm.novi.account.AccountRepository;
import nl.veenm.novi.menuitems.MenuItem;
import nl.veenm.novi.menuitems.MenuItemRepository;
import nl.veenm.novi.placedOrderDetails.PlacedOrderDetails;
import nl.veenm.novi.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class PlacedOrderService {

    @PersistenceContext
    private EntityManager entityManager;
    private final PlacedOrderRepository placedOrderRepository;
    private final MenuItemRepository menuItemRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final AccountRepository accountRepository;


    private ArrayList<MenuItem> orderedMenuItems;

    private static long placedOrderIdCounter = 0;
    private static long placedOrderDetailsIdCounter = 0;

    public static synchronized long createPlacedOrderID(){
        placedOrderIdCounter++;
        return placedOrderIdCounter;
    } public static synchronized long createPlacedOrderDetailsID(){
        placedOrderDetailsIdCounter++;
        return placedOrderDetailsIdCounter;
    }

    @Autowired
    public PlacedOrderService(PlacedOrderRepository placedOrderRepository, MenuItemRepository menuItemRepository, UserDetailsServiceImpl userDetailsService, AccountRepository accountRepository, ArrayList<MenuItem> orderedMenuItems) {
        this.placedOrderRepository = placedOrderRepository;
        this.menuItemRepository = menuItemRepository;
        this.userDetailsService = userDetailsService;
        this.accountRepository = accountRepository;
        this.orderedMenuItems = orderedMenuItems;
    }

    public List<PlacedOrder> getOrders(){
        return placedOrderRepository.findAll();
    }

    public List<MenuItem> getOrderedItems(){
        return orderedMenuItems;


    }

    public String addNewItem(Long itemId){
        System.out.println(itemId);
        Optional<MenuItem> itemFind = menuItemRepository.findById(itemId);
        MenuItem menuItem = itemFind.get();
        System.out.println(menuItem.getName());


        orderedMenuItems.add(menuItem);
        System.out.println(menuItem.getName() + " has been added.");

        return menuItem.getName() + "has been added";
    }

    @Transactional
    public ResponseEntity placeOrder(String payment, boolean delivery) {
        PlacedOrder newOrder = new PlacedOrder();
        PlacedOrderDetails newOrderDetails = new PlacedOrderDetails();


        double price = 0;
        int amount = 0;
        Long customerId;
        Optional<Account> accountOptional = accountRepository.findAccountByEmail(userDetailsService.userEmail);
        if (accountOptional.isPresent()) {
            customerId = accountOptional.get().getId();
        } else {
            throw new IllegalStateException("AccountId not found");
        }

        LocalDate date = LocalDate.now();

        for (MenuItem item : orderedMenuItems) {
            price = price + item.getPrice();
            amount++;

        }



        newOrder.setId(createPlacedOrderID());
        System.out.println("OrderId: " + newOrder.getId());
        newOrder.setAmount((float) price);
        newOrder.setAmountOfServing(amount);
        newOrder.setOrderDate(date);
        newOrder.setCustomerId(customerId);
        newOrder.setDelivery(delivery);
        newOrder.setPayment(payment);

        System.out.println(newOrder.getPayment() + " " + newOrder.isDelivery());




        if (newOrder.getAmount() > 0) {
            placedOrderRepository.save(newOrder);

//            entityManager.createNativeQuery("INSERT INTO placed_order (id,amount, amount_of_serving, customer_id, delivery, order_date, payment) VALUES  (?,?,?,?,?,?,?)")
//                    .setParameter(1, newOrder.getId())
//                    .setParameter(2, newOrder.getAmount())
//                    .setParameter(3, newOrder.getAmountOfServing())
//                    .setParameter(4, newOrder.getCustomerId())
//                    .setParameter(5, newOrder.isDelivery())
//                    .setParameter(6, newOrder.getOrderDate())
//                    .setParameter(7, newOrder.getPayment())
//                    .executeUpdate();

            long lastId = 0L;
            int lastQuantity = 0;
            for (MenuItem item: orderedMenuItems) {

                System.out.println("item.getId() = " + item.getId());
                System.out.println("lastId = " + lastId);
                if(item.getId() == lastId){
                    int quantity = lastQuantity + 1;
                    System.out.println("lastQuantity + 1 = " + lastQuantity);
                    entityManager.createNativeQuery("DELETE FROM placed_order_details WHERE item_id=?")
                            .setParameter(1,item.getId())
                            .executeUpdate();
                    entityManager.createNativeQuery("INSERT INTO placed_order_details (id, item_id, placed_order_id, quantity) VALUES (?,?,?,?)")
                            .setParameter(1,createPlacedOrderDetailsID())
                            .setParameter(2,newOrderDetails.getItemId())
                            .setParameter(3,newOrderDetails.getPlacedOrderId())
                            .setParameter(4,quantity)
                            .executeUpdate();
                    System.out.println("Quantity is aangepast met 1");
                    System.out.println("Quantity is nu: " + quantity);

                    System.out.println("newQuantity = " + newOrderDetails.getQuantity());

                    lastId = Math.toIntExact(newOrderDetails.getItemId());
                    lastQuantity = quantity;

                    System.out.println("lastQuantity = " + lastQuantity);

                }
                else {
                    newOrderDetails.setItemId(item.getId());
                    newOrderDetails.setPlacedOrderId(newOrder.getId());
                    newOrderDetails.setQuantity(1);

                    entityManager.createNativeQuery("INSERT INTO placed_order_details (id, item_id, placed_order_id, quantity) VALUES (?,?,?,?)")
                            .setParameter(1,createPlacedOrderDetailsID())
                            .setParameter(2,newOrderDetails.getItemId())
                            .setParameter(3,newOrderDetails.getPlacedOrderId())
                            .setParameter(4,newOrderDetails.getQuantity())
                            .executeUpdate();
                    lastId = newOrderDetails.getItemId();
                    lastQuantity = 1;
                    System.out.println(lastQuantity);

                }
        }

            newOrder.setId(null);
            newOrder.setAmount(0.00F);
            newOrder.setAmountOfServing(null);
            newOrder.setOrderDate(null);
            newOrder.setCustomerId(null);
            newOrder.setDelivery(false);
            newOrder.setPayment(null);
            orderedMenuItems.clear();



            return new ResponseEntity<>("Thank you for ordering at restaurant NOVI!", HttpStatus.OK);
        }
        else{
            placedOrderIdCounter = placedOrderIdCounter - 1;
            return new ResponseEntity<>("No items found in order",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
