package uzap.com.ua.twohourdelivery.util;


import android.widget.TextView;

import uzap.com.ua.twohourdelivery.data.UserProfile;

public class ProfileSetText {

    public static String setName(UserProfile dataProfile, TextView textView) {
        String text = "";
        if (dataProfile == null || dataProfile.getName() == null || dataProfile.getName().equals("")) {
            textView.setHint("Введите имя");
        } else {
            textView.setText(dataProfile.getName());
        }
        return text;
    }

    public static String setPhone(UserProfile dataProfile, TextView textView) {
        String text = "";
        if (dataProfile == null || dataProfile.getPhone() == null || dataProfile.getPhone().equals("")) {
            textView.setHint("Введите телефон");
        } else {
            textView.setText(dataProfile.getPhone());
        }
        return text;
    }
}