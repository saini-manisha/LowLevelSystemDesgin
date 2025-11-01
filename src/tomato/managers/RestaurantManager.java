package tomato.managers;

import tomato.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

// singleton
public class RestaurantManager {
    private List<Restaurant> restaurants=new ArrayList<>();
    private static RestaurantManager instance=null;

    private RestaurantManager(){

    }

    public static RestaurantManager getInstance(){
        if(instance==null){
            instance=new RestaurantManager();
        }
        return instance;
    }

    public void addRestaurant(Restaurant r){
        restaurants.add(r);
    }

    public List<Restaurant> searchByLocation(String loc){
        List<Restaurant> result=new ArrayList<>();
        loc=loc.toLowerCase();

        for(Restaurant r:restaurants){
            String r1=r.getLocation().toLowerCase();
            if(r1.equals(loc)){
                result.add(r);
            }
        }
        return result;
    }

}
