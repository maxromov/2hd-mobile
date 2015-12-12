package uzap.com.ua.twohourdelivery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

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
        holder.tvTimeAgo.setText(order.getTime());
        holder.tvAddressFrom.setText(order.getAddressFrom());
        holder.tvAddressTo.setText(order.getAddressTo());
        holder.tvOrderPrice.setText(order.getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView tvTimeAgo, tvAddressFrom, tvAddressTo, tvOrderPrice;

        public OrderViewHolder(View itemView) {
            super(itemView);
            tvTimeAgo = (TextView) itemView.findViewById(R.id.tvTimeAgo);
            tvAddressFrom = (TextView) itemView.findViewById(R.id.tvAddressFrom);
            tvAddressTo = (TextView) itemView.findViewById(R.id.tvAddressTo);
            tvOrderPrice = (TextView) itemView.findViewById(R.id.tvOrderPrice);
        }
    }
}
