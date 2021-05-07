package nl.veenm.novi.placedOrder;

import nl.veenm.novi.account.Account;
import nl.veenm.novi.account.AccountRepository;
import nl.veenm.novi.delivery.Delivery;
import nl.veenm.novi.delivery.DeliveryRepository;
import nl.veenm.novi.menuitems.MenuItem;
import nl.veenm.novi.menuitems.MenuItemRepository;
import nl.veenm.novi.pickup.Pickup;
import nl.veenm.novi.pickup.PickupRepository;
import nl.veenm.novi.placedOrderDetails.PlacedOrderDetails;
import nl.veenm.novi.security.UserDetailsServiceImpl;
import nl.veenm.novi.menuitems.SortMenuItems;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final DeliveryRepository deliveryRepository;
    private final PickupRepository pickupRepository;


    private ArrayList<MenuItem> orderedMenuItems;

    private static long placedOrderIdCounter = 0;
    private static long placedOrderDetailsIdCounter = 0;
    private static long lastOrderId = 0;

    public static synchronized long createPlacedOrderID(){
        placedOrderIdCounter++;
        return placedOrderIdCounter;
    } public static synchronized long createPlacedOrderDetailsID(){
        placedOrderDetailsIdCounter++;
        return placedOrderDetailsIdCounter;
    }

    @Autowired
    public PlacedOrderService(PlacedOrderRepository placedOrderRepository, MenuItemRepository menuItemRepository, UserDetailsServiceImpl userDetailsService, AccountRepository accountRepository, DeliveryRepository deliveryRepository, PickupRepository pickupRepository, ArrayList<MenuItem> orderedMenuItems) {
        this.placedOrderRepository = placedOrderRepository;
        this.menuItemRepository = menuItemRepository;
        this.userDetailsService = userDetailsService;
        this.accountRepository = accountRepository;
        this.deliveryRepository = deliveryRepository;
        this.pickupRepository = pickupRepository;
        this.orderedMenuItems = orderedMenuItems;
    }

    public List<PlacedOrder> getOrders(){
        ArrayList<PlacedOrder> placedOrders = new ArrayList<PlacedOrder>();
        placedOrders.addAll(placedOrderRepository.findAll());
        int i = 0;
        for (PlacedOrder placedOrder: placedOrders) {

            if(placedOrder.getStatus().equalsIgnoreCase("order_delivered") ||placedOrder.getStatus().equalsIgnoreCase("order_picked_up") ){
                placedOrders.remove(i);
            }
            i++;

        }
        return placedOrders;
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

        Collections.sort(orderedMenuItems, new SortMenuItems());
        Collections.reverse(orderedMenuItems);


        for (MenuItem menuitem:orderedMenuItems) {
            System.out.println("Id:" + menuitem.getId());
            System.out.println("Name:" + menuitem.getName());
            System.out.println("Price:" + menuitem.getPrice());

        }
        return menuItem.getName() + " has been added";


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
        newOrder.setStatus("Order_Placed");

        System.out.println(newOrder.getPayment() + " " + newOrder.isDelivery());




        if (newOrder.getAmount() > 0) {
            placedOrderRepository.save(newOrder);


            long lastId = 0L;
            int lastQuantity = 0;
            for (MenuItem item: orderedMenuItems) {

                System.out.println("item.getId() = " + item.getId());
                System.out.println("lastId = " + lastId);
                if(item.getId() == lastId && newOrder.getId() != lastOrderId){
                    int quantity = lastQuantity + 1;
                    System.out.println("lastQuantity + 1 = " + lastQuantity);
                    entityManager.createNativeQuery("DELETE FROM placed_order_details WHERE item_id=?")
                            .setParameter(1,item.getId())
                            .executeUpdate();
                    entityManager.createNativeQuery("INSERT INTO placed_order_details (id, item_id, placed_order_id, quantity) VALUES (?,?,?,?)")
                            .setParameter(1,placedOrderDetailsIdCounter)
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

            lastOrderId = newOrder.getId();

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

    @Transactional
    public String readyOrder(Long orderId) {
        Optional<PlacedOrder> orderDetails = placedOrderRepository.findById(orderId);

        if (orderDetails.get().isDelivery()){


            //generate new delivery record
            Delivery createDelivery = new Delivery();
            createDelivery.setAmount(orderDetails.get().getAmount());
            createDelivery.setOrderId(orderDetails.get().getId());
            createDelivery.setCustomerId(orderDetails.get().getCustomerId());
            createDelivery.setCustomerAddress(accountRepository.findById(orderDetails.get().getCustomerId()).get().getAddress());
            createDelivery.setCustomerCity(accountRepository.findById(orderDetails.get().getCustomerId()).get().getCity());
            createDelivery.setCustomerPhone(accountRepository.findById(orderDetails.get().getCustomerId()).get().getPhone());
            createDelivery.setStatus("ready_for_delivery");
            if(orderDetails.get().getPayment().equalsIgnoreCase("online")){
                createDelivery.setPaid(true);
            }
            else{
                createDelivery.setPaid(false);
            }
            deliveryRepository.saveAll(List.of(createDelivery));
        }
        else {


            Pickup createPickup = new Pickup();

            createPickup.setCustomerFirstName(accountRepository.findById(orderDetails.get().getCustomerId()).get().getFirstName());
            createPickup.setOrderId(orderDetails.get().getId());
            createPickup.setAmount(orderDetails.get().getAmount());
            createPickup.setCustomerId(orderDetails.get().getCustomerId());
            createPickup.setCustomerPhone(accountRepository.findById(orderDetails.get().getCustomerId()).get().getPhone());
            createPickup.setStatus("ready_for_pickup");
            if(orderDetails.get().getPayment().equalsIgnoreCase("online")){
                createPickup.setPaid(true);
            }
            else{
                createPickup.setPaid(false);
            }
            pickupRepository.saveAll(List.of(createPickup));
        }

        //update status
        entityManager.createNativeQuery("UPDATE placed_order SET status = ? WHERE id = ?")
                .setParameter(1, orderDetails.get().getStatus())
                .setParameter(2,orderDetails.get().getId())
                .executeUpdate();
        System.out.println("orderDetails Status: " + orderDetails.get().getStatus());
        System.out.println("orderDetails Id: " + orderDetails.get().getId());

        return "Order status has been updated";
    }
}
