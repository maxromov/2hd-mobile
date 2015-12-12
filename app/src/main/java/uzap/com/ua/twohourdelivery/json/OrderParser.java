package uzap.com.ua.twohourdelivery.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import uzap.com.ua.twohourdelivery.data.Order;

public class OrderParser {
    public static ArrayList<Order> parseJSONResponse(JSONObject response) {

        ArrayList<Order> listOrders = new ArrayList<>();

        if (response != null && response.length() > 0) {
            try {
                JSONArray arrayArticle = response.getJSONArray("posts");
                for (int i = 0; i < arrayArticle.length(); i++) {
                    JSONObject currentArticle = arrayArticle.getJSONObject(i);

                    Order order = new Order();


                    listOrders.add(order);

                }
            } catch (JSONException e) {

            }

        }
        return listOrders;
    }
}
