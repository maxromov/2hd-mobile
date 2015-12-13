package uzap.com.ua.twohourdelivery.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import uzap.com.ua.twohourdelivery.AppContext;
import uzap.com.ua.twohourdelivery.callback.OrderListListener;
import uzap.com.ua.twohourdelivery.data.Order;

public class TestTask extends AsyncTask<Void, Void, ArrayList<Order>> {

    private ProgressDialog pDialog;
    private Context context;
    private OrderListListener myComponent;
    private ArrayList<Order> list;

    public TestTask(Context context, OrderListListener myComponent, ArrayList<Order> list) {
        this.context = context;
        this.myComponent = myComponent;
        this.list = list;
        pDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog.setCancelable(false);
        pDialog.setMessage("Обновление заявок");
        pDialog.show();
    }

    @Override
    protected ArrayList<Order> doInBackground(Void... params) {
        ArrayList<Order> orderList = list;
        // orderList.add(new Order("5 мин.", "Киев", "Бровары", "1200 грн."));
        // AppContext.getWritableDatabase().insertOrder("5 мин.", "Киев", "Бровары", "1200 грн.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    protected void onPostExecute(ArrayList<Order> list) {
        super.onPostExecute(list);
        myComponent.orderListener(list);
        pDialog.dismiss();
    }
}
