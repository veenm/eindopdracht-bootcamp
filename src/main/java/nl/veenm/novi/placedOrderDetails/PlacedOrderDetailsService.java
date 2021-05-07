package nl.veenm.novi.placedOrderDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlacedOrderDetailsService {

    private final PlacedOrderDetailsRepository placedOrderDetailsRepository;

    @Autowired
    public PlacedOrderDetailsService(PlacedOrderDetailsRepository placedOrderDetailsRepository) {
        this.placedOrderDetailsRepository = placedOrderDetailsRepository;
    }

    public List<PlacedOrderDetails> getOrderDetails(){
        return placedOrderDetailsRepository.findAll();
    }

    public List<PlacedOrderDetails> getOrderDetailsById(Long placedOrderID) {
        ArrayList<PlacedOrderDetails> placedOrdersDetails = new ArrayList<>();

        placedOrdersDetails.addAll(placedOrderDetailsRepository.findAll());
        int i = 0;

        for (PlacedOrderDetails placedOrderDetails: placedOrdersDetails) {
            if(!placedOrderDetails.getPlacedOrderId().equals(placedOrderID)){
                placedOrdersDetails.remove(i);
            }
            i++;
        }

        return placedOrdersDetails;
    }
}
