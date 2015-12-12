package uzap.com.ua.twohourdelivery;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import uzap.com.ua.twohourdelivery.data.DBTwoHour;

public class AppContext extends Application {

    public static final String TAG = AppContext.class
            .getSimpleName();
    private static AppContext sInstance;
    private static DBTwoHour mDataBase;
    private RequestQueue mRequestQueue;

    public static synchronized AppContext getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

    public synchronized static DBTwoHour getWritableDatabase() {
        if (mDataBase == null) {
            mDataBase = new DBTwoHour(getAppContext());
        }
        return mDataBase;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        mDataBase = new DBTwoHour(this);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}

