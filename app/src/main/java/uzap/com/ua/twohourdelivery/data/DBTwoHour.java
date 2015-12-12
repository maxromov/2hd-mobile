package uzap.com.ua.twohourdelivery.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBTwoHour {
    private DBHelper mHelper;
    private SQLiteDatabase mDatabase;


    public DBTwoHour(Context context) {
        mHelper = new DBHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    private static class DBHelper extends SQLiteOpenHelper {
        public static final String ID = "_id";
        private static final String TAG = "com.twohour.db";
        private static final String DATABASE_NAME = "twohour.db";
        private static final int DATABASE_VERSION = 1;


        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
