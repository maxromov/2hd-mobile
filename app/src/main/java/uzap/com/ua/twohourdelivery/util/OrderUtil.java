package uzap.com.ua.twohourdelivery.util;

import android.util.Log;

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
        Log.d("wtf", "OrderUtil");
        JSONArray response = OrderRequestor.sendJsonRequest(requestQueue, "http://2hd.com.ua/api/v1/orders");
        ArrayList<Order> listArticles = OrderParser.parseJSONResponse(response);
        AppContext.getWritableDatabase().insertArticlesInDataBase(listArticles);
        Log.d("wtf", "list = " + listArticles.toString());
        return listArticles;
    }
}
