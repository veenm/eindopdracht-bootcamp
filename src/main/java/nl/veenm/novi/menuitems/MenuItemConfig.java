package nl.veenm.novi.menuitems;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;



@Configuration
public class MenuItemConfig {

    @Bean
    CommandLineRunner configItems(MenuItemRepository repository) {
        return args -> {
            MenuItem menuItem1 = new MenuItem("Zuppa di Pomodoro","huisgemaakte tomatensoep, groene pesto", 5.00 );
            MenuItem menuItem2 = new MenuItem("Insalata Cesare","ruccola, gegrilde kip, ansjovis, parmezaanse kaas, mosterdvinaigerette", 10.00 );
            MenuItem menuItem3 = new MenuItem("Carpaccio di Manzo","ossenhaas, mesclun sla, pijnboompitten, groene pesto, truffelcreme", 12.00 );
            MenuItem menuItem4 = new MenuItem("Pasta alla Bolognese","rundergehakt, tomatensaus", 10.00 );
            MenuItem menuItem5 = new MenuItem("Pasta alla Cabonara","pancette spek, champignons, roomsaus", 11.00 );
            MenuItem menuItem6 = new MenuItem("Pasta con Verdure","verse groenten, tomatensaus", 11.50 );
            MenuItem menuItem7 = new MenuItem("Lasagne al Forno","traditioneel lasagne met rundergehakt", 10.00 );
            MenuItem menuItem8 = new MenuItem("Lasagne al Gorgonzola","gorgonzola kaas, ruccola sla", 11.50 );
            MenuItem menuItem9 = new MenuItem("Lasasgne Salmone e Spinaci","ambachtelijk gerookte zalm filet, verse spinazie", 13.50 );
            MenuItem menuItem10 = new MenuItem("Tiramisu della Casa","huisgemaakte tiramisu", 7.00 );
            MenuItem menuItem11 = new MenuItem("Cheesecake al Limone","citroen cheesecake, aardbeien basilicum saus", 7.00 );
            MenuItem menuItem12 = new MenuItem("Panna Cotta con Salsa allo Zenzero","huisgemaakte roompudding met gembersaus", 7.00 );


        repository.saveAll(List.of(menuItem1, menuItem2, menuItem3, menuItem4, menuItem5, menuItem6, menuItem7, menuItem8, menuItem9, menuItem10, menuItem11, menuItem12));
        };


    }
}
