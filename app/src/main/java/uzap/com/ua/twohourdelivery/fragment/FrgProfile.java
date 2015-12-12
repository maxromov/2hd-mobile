package uzap.com.ua.twohourdelivery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import uzap.com.ua.twohourdelivery.AppContext;
import uzap.com.ua.twohourdelivery.R;
import uzap.com.ua.twohourdelivery.activity.MainActivity;
import uzap.com.ua.twohourdelivery.data.UserProfile;
import uzap.com.ua.twohourdelivery.dialog.DialogPhoneProfile;
import uzap.com.ua.twohourdelivery.util.ProfileSetText;


public class FrgProfile extends CommonFragment {

    private static EditText subTitle;
    private static TextView subTitlePhone;
    private static UserProfile userProfile;

    public static void updateAll() {
        userProfile = AppContext.getWritableDatabase().getProfile();
        ProfileSetText.setName(userProfile, subTitle);
        ProfileSetText.setPhone(userProfile, subTitlePhone);
        AppContext.getWritableDatabase().insertProfile(subTitle.getText().toString(), subTitlePhone.getText().toString());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userProfile = AppContext.getWritableDatabase().getProfile();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_profile, container, false);
        Log.d("wtf", "frgOpen");

        subTitle = (EditText) rootView.findViewById(R.id.subTitle);
        subTitle.setFocusable(false);
        subTitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                subTitle.setFocusable(true);
                subTitle.setFocusableInTouchMode(true);
                subTitle.findFocus();
                return false;
            }
        });
        subTitlePhone = (TextView) rootView.findViewById(R.id.subTitlePhone);
        subTitlePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogPhoneProfile myDialogFragment = new DialogPhoneProfile();
                myDialogFragment.show(MainActivity.fm, "dialog_phone_profile");
            }
        });

        ProfileSetText.setName(userProfile, subTitle);
        ProfileSetText.setPhone(userProfile, subTitlePhone);

        goneFab();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.frg_profile);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppContext.getWritableDatabase().insertProfile(subTitle.getText().toString(), subTitlePhone.getText().toString());
    }

    @Override
    public String getTitle() {
        return FrgOpenOrder.class.getName();
    }
}
