package uzap.com.ua.twohourdelivery.fragment;

import android.app.Activity;
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

import java.util.ArrayList;

import uzap.com.ua.twohourdelivery.AppContext;
import uzap.com.ua.twohourdelivery.R;
import uzap.com.ua.twohourdelivery.activity.DetailOrderActivity;
import uzap.com.ua.twohourdelivery.activity.MainActivity;
import uzap.com.ua.twohourdelivery.adapter.OpenOrderAdapter;
import uzap.com.ua.twohourdelivery.callback.OnLoadOrderListListener;
import uzap.com.ua.twohourdelivery.data.Order;
import uzap.com.ua.twohourdelivery.data.UserProfile;
import uzap.com.ua.twohourdelivery.dialog.DialogPhone;
import uzap.com.ua.twohourdelivery.task.GetAllOrderTask;
import uzap.com.ua.twohourdelivery.util.RecyclerItemClickListener;

public class FrgOpenOrder extends CommonFragment implements OnLoadOrderListListener {

    final String LOG_TAG = "myLogs";
    private RecyclerView recyclerView;
    private OpenOrderAdapter adapter;
    private ArrayList<Order> orderList;
    private SwipeRefreshLayout swipeRefreshLayout;
    //TODO: test
    private ProgressDialog pDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AppContext.getWritableDatabase().getOrderList() == null) {
        // new GetAllOrderTask(this, getActivity()).execute();
//        }
        Log.d(LOG_TAG, "Fragment1 onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_open_order, container, false);
        Log.d("wtf", "frgOpen");
        showFab();

        Log.d(LOG_TAG, "Fragment1 onCreateView");
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


//                ((MainActivity) getActivity()).clickFabButton(new OnLoadOrderListListener() {
//                    @Override
//                    public void onLoadOrderList(ArrayList<Order> list) {
//                        adapter.updateOrder(list);
//                    }
//                });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.frg_open_order);
        Log.d(LOG_TAG, "Fragment1 onResume");
    }

    public void onLoadListener() {
        new GetAllOrderTask(this, getActivity()).execute();
    }

    @Override
    public String getTitle() {
        return FrgOpenOrder.class.getName();
    }

    @Override
    public void onLoadOrderList(ArrayList<Order> list) {
        adapter.updateOrder(list);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(LOG_TAG, "Fragment1 onAttach");
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG_TAG, "Fragment1 onActivityCreated");
    }

    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "Fragment1 onStart");
    }


    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "Fragment1 onPause");
    }

    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "Fragment1 onStop");
    }

    public void onDestroyView() {
        super.onDestroyView();
        Log.d(LOG_TAG, "Fragment1 onDestroyView");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "Fragment1 onDestroy");
    }

    public void onDetach() {
        super.onDetach();
        Log.d(LOG_TAG, "Fragment1 onDetach");
    }
}
