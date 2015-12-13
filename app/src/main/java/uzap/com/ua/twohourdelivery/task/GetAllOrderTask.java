package uzap.com.ua.twohourdelivery.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import uzap.com.ua.twohourdelivery.VolleySingleton;
import uzap.com.ua.twohourdelivery.callback.OnLoadOrderListListener;
import uzap.com.ua.twohourdelivery.data.Order;
import uzap.com.ua.twohourdelivery.util.OrderUtil;

public class GetAllOrderTask extends AsyncTask<Void, Void, ArrayList<Order>> {
    private OnLoadOrderListListener myComponent;
    private Context context;
    private ProgressDialog pDialog;

    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public GetAllOrderTask(OnLoadOrderListListener myComponent, Context context) {
        this.context = context;
        this.myComponent = myComponent;
        pDialog = new ProgressDialog(context);
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog.setCancelable(false);
        pDialog.setMessage("Обновление заявок...");
        pDialog.show();
    }

    @Override
    protected ArrayList<Order> doInBackground(Void... voids) {
        ArrayList<Order> list = OrderUtil.loadListOrders(requestQueue);
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<Order> orders) {
        Log.d("wtf", "onPostExecute");
        if (myComponent != null) {
            myComponent.onLoadOrderList(orders);
            pDialog.hide();
            Log.d("wtf", orders.toString());
        }
    }
}
