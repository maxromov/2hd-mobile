package uzap.com.ua.twohourdelivery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uzap.com.ua.twohourdelivery.R;
import uzap.com.ua.twohourdelivery.activity.MainActivity;

public class FrgOpenOrder extends CommonFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_open_order, container, false);
        Log.d("wtf", "frgOpen");

        showFab();
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
}
