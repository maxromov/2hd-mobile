package uzap.com.ua.twohourdelivery.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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
import uzap.com.ua.twohourdelivery.callback.OnLoadOrderListListener;
import uzap.com.ua.twohourdelivery.callback.OrderListListener;
import uzap.com.ua.twohourdelivery.data.Order;
import uzap.com.ua.twohourdelivery.data.UserProfile;
import uzap.com.ua.twohourdelivery.dialog.DialogPhone;
import uzap.com.ua.twohourdelivery.task.GetAllOrderTask;
import uzap.com.ua.twohourdelivery.task.TestTask;
import uzap.com.ua.twohourdelivery.util.RecyclerItemClickListener;

public class FrgOpenOrder extends CommonFragment implements SwipeRefreshLayout.OnRefreshListener, OnLoadOrderListListener {

    private RecyclerView recyclerView;
    private OpenOrderAdapter adapter;
    private ArrayList<Order> orderList;
    private SwipeRefreshLayout swipeRefreshLayout;
    //TODO: test
    private ProgressDialog pDialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppContext.getWritableDatabase().getOrderList() == null) {
            new GetAllOrderTask(this, getActivity()).execute();
        }
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
                        UserProfile userProfile = AppContext.getWritableDatabase().getProfile();


                        if (userProfile == null || userProfile.getPhone() == null) {
                            DialogPhone myDialogFragment = new DialogPhone();
                            myDialogFragment.show(MainActivity.fm, "dialog_phone");
                        } else {
                            Intent intent = new Intent(getActivity(), DetailOrderActivity.class);
                            intent.putExtra("Заява №" + position, "order");
                            startActivity(intent);
                        }
                        }
                }));




        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
        swipeRefreshLayout.setOnRefreshListener(this);

        ((MainActivity) getActivity()).clickFabButton(this);

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
        new GetAllOrderTask(this, getActivity()).execute();
    }


    @Override
    public void onLoadOrderList(ArrayList<Order> list) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        adapter.updateOrder(list);
    }
}
