package uzap.com.ua.twohourdelivery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import uzap.com.ua.twohourdelivery.AppContext;
import uzap.com.ua.twohourdelivery.R;
import uzap.com.ua.twohourdelivery.activity.DetailOrderActivity;
import uzap.com.ua.twohourdelivery.activity.MainActivity;
import uzap.com.ua.twohourdelivery.adapter.OpenOrderAdapter;
import uzap.com.ua.twohourdelivery.data.Order;
import uzap.com.ua.twohourdelivery.data.UserProfile;
import uzap.com.ua.twohourdelivery.dialog.DialogPhone;
import uzap.com.ua.twohourdelivery.util.RecyclerItemClickListener;

/**
 * Created by marazmone on 12.12.2015.
 */
public class FrgCurrentOrder extends CommonFragment {

    private RecyclerView recyclerView;
    private OpenOrderAdapter adapter;
    private ArrayList<Order> orderList;
    private TextView tvNoCurrentOrder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        orderList = new ArrayList<>();
//        orderList.add(new Order("5 мин.", "Киев", "Бровары", "1200 грн."));

        orderList = AppContext.getWritableDatabase().getCurrentOrderList();

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_current_order, container, false);
        Log.d("wtf", "frgOpen");
        tvNoCurrentOrder = (TextView) rootView.findViewById(R.id.tvNoCurrentOrder);
        tvNoCurrentOrder.setVisibility(View.GONE);
        if (orderList != null) {
            recyclerView = (RecyclerView) rootView.findViewById(R.id.rvOpenOrder);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(manager);
            adapter = new OpenOrderAdapter(getActivity(), orderList);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
                    new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            Intent intent = new Intent(getActivity(), DetailOrderActivity.class);
                            intent.putExtra("isCurrent", true);
                            intent.putExtra("time", adapter.getItem(position).getTime());
                            intent.putExtra("from", adapter.getItem(position).getAddressFrom());
                            intent.putExtra("to", adapter.getItem(position).getAddressTo());
                            intent.putExtra("priceDelivery", adapter.getItem(position).getPricePackage());
                            intent.putExtra("pricePackage", adapter.getItem(position).getPriceDelivery());
                            startActivity(intent);

                        }
                    }));
        } else {
            tvNoCurrentOrder.setVisibility(View.VISIBLE);
        }


        goneFab();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.frg_current_order);
    }

    @Override
    public String getTitle() {
        return FrgOpenOrder.class.getName();
    }
}