package tomato.factory;

import tomato.models.*;
import tomato.strategies.PaymentStrategy;
import tomato.utils.TimeUtils;

import java.util.List;

public class NowOrderFactory implements  OrderFactory{

    public Order createOrder(User user, Cart cart, Restaurant restaurant, List<MenuItem> menuItems,
                             PaymentStrategy paymentStrategy, double totalCost, String orderType){
        Order order=null;

        if(orderType.equals("Delhivery")){
            DeliveryOrder deliveryOrder =new DeliveryOrder();
            deliveryOrder.setUserAddress(user.getAddress());
            order=deliveryOrder;
        }
        else{
            PickupOrder pickupOrder=new PickupOrder();
            pickupOrder.setRestaurantAddress(restaurant.getLocation());
            order=pickupOrder;

        }
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setItems(menuItems);
        order.setPaymentStrategy(paymentStrategy);
        order.setScheduled(TimeUtils.getCurrentTime());
        order.setTotal(totalCost);
        return order;
    }
}
