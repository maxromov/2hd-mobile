package uzap.com.ua.twohourdelivery.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import uzap.com.ua.twohourdelivery.R;
import uzap.com.ua.twohourdelivery.activity.DetailOrderActivity;
import uzap.com.ua.twohourdelivery.activity.MainActivity;
import uzap.com.ua.twohourdelivery.adapter.OpenOrderAdapter;
import uzap.com.ua.twohourdelivery.data.Order;
import uzap.com.ua.twohourdelivery.task.TestTask;
import uzap.com.ua.twohourdelivery.util.RecyclerItemClickListener;

public class FrgOpenOrder extends CommonFragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private OpenOrderAdapter adapter;
    private ArrayList<Order> orderList;
    private SwipeRefreshLayout swipeRefreshLayout;
    //TODO: test
    private ProgressDialog pDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orderList.add(new Order(i + " мин.", "Киев", "Бровары", "1200 грн."));
        }

        pDialog = new ProgressDialog(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_open_order, container, false);
        Log.d("wtf", "frgOpen");
        showFab();

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
                        intent.putExtra("Заява №" + position, "order");
                        startActivity(intent);
                    }
                }));


        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
        swipeRefreshLayout.setOnRefreshListener(this);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.frg_open_order);
    }

    @Override
    public String getTitle() {
        return FrgOpenOrder.class.getName();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        new TestTask(getActivity()).execute();
    }

}