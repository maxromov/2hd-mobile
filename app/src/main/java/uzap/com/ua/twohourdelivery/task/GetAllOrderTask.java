package uzap.com.ua.twohourdelivery.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import uzap.com.ua.twohourdelivery.AppContext;
import uzap.com.ua.twohourdelivery.callback.OnLoadOrderListListener;
import uzap.com.ua.twohourdelivery.data.Order;
import uzap.com.ua.twohourdelivery.util.OrderUtil;

public class GetAllOrderTask extends AsyncTask<Void, Void, ArrayList<Order>> {
    private OnLoadOrderListListener myComponent;

    private Context context;
    private ProgressDialog progressDialog;

    public GetAllOrderTask(OnLoadOrderListListener myComponent, Context context,
                           boolean loadDialog) {
        this.context = context;
        this.myComponent = myComponent;
    }


    @Override
    protected ArrayList<Order> doInBackground(Void... voids) {
        return OrderUtil.loadListOrders(AppContext.getInstance().getRequestQueue());
    }

    @Override
    protected void onPostExecute(ArrayList<Order> orders) {
        if (myComponent != null) {
            myComponent.onLoadOrderList(orders);

        }
    }
}
