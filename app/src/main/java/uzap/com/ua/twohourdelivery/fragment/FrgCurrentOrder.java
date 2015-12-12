package uzap.com.ua.twohourdelivery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uzap.com.ua.twohourdelivery.R;
import uzap.com.ua.twohourdelivery.activity.MainActivity;

/**
 * Created by marazmone on 12.12.2015.
 */
public class FrgCurrentOrder extends CommonFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_current_order, container, false);
        Log.d("wtf", "frgOpen");

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