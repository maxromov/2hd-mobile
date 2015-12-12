package uzap.com.ua.twohourdelivery.util;

import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import uzap.com.ua.twohourdelivery.AppContext;
import uzap.com.ua.twohourdelivery.api.OrderRequestor;
import uzap.com.ua.twohourdelivery.data.Order;
import uzap.com.ua.twohourdelivery.json.OrderParser;

public class OrderUtil {
    public static ArrayList<Order> loadListOrders(RequestQueue requestQueue) {
        JSONArray response = OrderRequestor.sendJsonRequest(requestQueue, "http://2hd.com.ua/orders/");
        ArrayList<Order> listArticles = OrderParser.parseJSONResponse(response);
        AppContext.getWritableDatabase().insertArticlesInDataBase(listArticles);
        return listArticles;
    }
}
