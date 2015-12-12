package uzap.com.ua.twohourdelivery.callback;


import java.util.ArrayList;

import uzap.com.ua.twohourdelivery.data.Order;

public interface OnLoadOrderListListener {
    void onLoadOrderList(ArrayList<Order> list);
}
