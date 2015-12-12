package uzap.com.ua.twohourdelivery.callback;

import java.util.ArrayList;

import uzap.com.ua.twohourdelivery.data.Order;

/**
 * Created by marazmone on 12.12.2015.
 */
public interface OrderListListener {
    void orderListener(ArrayList<Order> list);
}
