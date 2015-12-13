package uzap.com.ua.twohourdelivery.util;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPref {

    private final static String APP_PREFERENCES = "ua.com.2twd";
    private SharedPreferences sharedPreferences;


    public SharedPref(Context context) {
        this.sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public int getFragment() {
        return sharedPreferences.getInt("fragment_number", 1);
    }

    public void setFragment(int i) {
        sharedPreferences.edit().putInt("fragment_number", i).apply();
    }

    public void deleteSharedPrefFrgOther() {
        sharedPreferences.edit().clear().apply();
    }
}
