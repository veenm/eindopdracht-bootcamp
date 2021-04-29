package nl.veenm.novi.placedOrderDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
