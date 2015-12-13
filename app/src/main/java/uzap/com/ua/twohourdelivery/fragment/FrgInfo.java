package uzap.com.ua.twohourdelivery.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import uzap.com.ua.twohourdelivery.R;
import uzap.com.ua.twohourdelivery.activity.MainActivity;

/**
 * Created by marazmone on 12.12.2015.
 */
public class FrgInfo extends CommonFragment {

    private TextView tvSite;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_info, container, false);
        Log.d("wtf", "frgOpen");

        tvSite = (TextView) rootView.findViewById(R.id.tvSite);
        tvSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://" + tvSite.getText().toString()));
                startActivity(browserIntent);
            }
        });

        goneFab();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.frg_info);
    }

    @Override
    public String getTitle() {
        return FrgOpenOrder.class.getName();
    }
}
