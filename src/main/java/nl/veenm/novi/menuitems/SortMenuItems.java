package nl.veenm.novi.menuitems;

import java.util.Comparator;

public class SortMenuItems implements Comparator<MenuItem> {



    @Override
    public int compare(MenuItem item1, MenuItem item2) {
        if(item1.getId() < item2.getId()){
            return 1;
        }
        return -1;
    }
}
