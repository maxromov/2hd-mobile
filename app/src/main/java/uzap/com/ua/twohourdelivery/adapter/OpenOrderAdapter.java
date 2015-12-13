package uzap.com.ua.twohourdelivery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import uzap.com.ua.twohourdelivery.R;
import uzap.com.ua.twohourdelivery.data.Order;


public class OpenOrderAdapter extends RecyclerView.Adapter<OpenOrderAdapter.OrderViewHolder> {

    private Context context;
    private ArrayList<Order> list;
    private LayoutInflater layoutInflater;

    public OpenOrderAdapter(Context context, ArrayList<Order> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    public void updateOrder(ArrayList<Order> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = layoutInflater.inflate(R.layout.item_list_order, parent, false);
        OrderViewHolder holder = new OrderViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        Order order = list.get(position);
        Calendar calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis() - order.getTime();
        long strTime = time / 60000;
        Log.d("wtfTime", "calendar = " + String.valueOf(calendar.getTimeInMillis()));
        Log.d("wtfTime", "time = " + String.valueOf(order.getTime()));

        holder.tvTimeAgo.setText(String.valueOf(strTime) + " мин. назад");
        holder.tvAddressFrom.setText(order.getAddressFrom());
        holder.tvAddressTo.setText(order.getAddressTo());
        holder.tvOrderPriceDelivery.setText(String.valueOf(order.getPriceDelivery()) + " грн.");
        holder.tvOrderPricePackage.setText(String.valueOf(order.getPricePackage()) + " грн.");
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView tvTimeAgo, tvAddressFrom, tvAddressTo, tvOrderPricePackage, tvOrderPriceDelivery;

        public OrderViewHolder(View itemView) {
            super(itemView);
            tvTimeAgo = (TextView) itemView.findViewById(R.id.tvTimeAgo);
            tvAddressFrom = (TextView) itemView.findViewById(R.id.tvAddressFrom);
            tvAddressTo = (TextView) itemView.findViewById(R.id.tvAddressTo);
            tvOrderPriceDelivery = (TextView) itemView.findViewById(R.id.tvOrderPriceDelivery);
            tvOrderPricePackage = (TextView) itemView.findViewById(R.id.tvOrderPricePackage);
        }
    }
}
