package uzap.com.ua.twohourdelivery.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import uzap.com.ua.twohourdelivery.data.Order;

public class OrderParser {
    public static ArrayList<Order> parseJSONResponse(JSONArray response) {


        ArrayList<Order> listOrders = new ArrayList<>();

        if (response != null && response.length() > 0) {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject currentOrder = response.getJSONObject(i);
                    int id = currentOrder.getInt("id");
                    long time = currentOrder.getLong("creation_date");
                    String from = currentOrder.getString("from_address");
                    String to = currentOrder.getString("delivery_address");
                    int deliveryCost = currentOrder.getInt("delivery_cost");
                    int packageCost = currentOrder.getInt("package_cost");

                    Order order = new Order();
                    order.setId(id);
                    order.setTime(time);
                    order.setAddressFrom(from);
                    order.setAddressTo(to);
                    order.setPricePackage(packageCost);
                    order.setPriceDelivery(deliveryCost);

                    listOrders.add(order);

                }
            } catch (JSONException e) {

            }
        }

        return listOrders;
    }
}
