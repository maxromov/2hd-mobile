package uzap.com.ua.twohourdelivery.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;


public class DBTwoHour {
    private DBHelper mHelper;
    private SQLiteDatabase mDatabase;
    private int lastOrder;

    public DBTwoHour(Context context) {
        mHelper = new DBHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    public void insertProfile(String name, String phone) {
        mDatabase.delete(DBHelper.PROFILE_TABLE, null, null);
        ContentValues values = new ContentValues();
        values.put(DBHelper.PROFILE_NAME, name);
        values.put(DBHelper.PROFILE_PHONE, phone);
        mDatabase.insert(DBHelper.PROFILE_TABLE, null, values);
    }

    public UserProfile getProfile() {
        UserProfile userProfile = new UserProfile();
        String[] columns = {
                DBHelper.PROFILE_NAME,
                DBHelper.PROFILE_PHONE
        };

        Cursor cursor = mDatabase.query(DBHelper.PROFILE_TABLE, columns, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                userProfile.setName(cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_NAME)));
                userProfile.setPhone(cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_PHONE)));

            } while (cursor.moveToNext());
            cursor.close();
        } else {
            return null;
        }
        return userProfile;
    }

    public void insertOrder(String time, String from, String to, String price) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.ORDER_TIME, time);
        values.put(DBHelper.ORDER_FROM, from);
        values.put(DBHelper.ORDER_TO, to);
        values.put(DBHelper.ORDER_PRICE, price);
        mDatabase.insert(DBHelper.ORDER_TABLE, null, values);
    }

    public void insertCurrentOrder(String time, String from, String to, String price) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.CURRENT_ORDER_TIME, time);
        values.put(DBHelper.CURRENT_ORDER_FROM, from);
        values.put(DBHelper.CURRENT_ORDER_TO, to);
        values.put(DBHelper.CURRENT_ORDER_PRICE, price);
        mDatabase.insert(DBHelper.CURRENT_ORDER_TABLE, null, values);
    }

    public void deleteCurrentOrder() {

        String[] columns = {
                DBHelper.ID, DBHelper.CURRENT_ORDER_TIME,
                DBHelper.CURRENT_ORDER_FROM, DBHelper.CURRENT_ORDER_TO,
                DBHelper.CURRENT_ORDER_PRICE
        };
        Cursor cursor = mDatabase.query(DBHelper.CURRENT_ORDER_TABLE, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToLast()) {
            lastOrder = cursor.getInt(cursor.getColumnIndex(DBHelper.ID));
            cursor.close();
        }

        mDatabase.delete(DBHelper.CURRENT_ORDER_TABLE, DBHelper.ID + "=" + lastOrder, null);
    }

    public ArrayList<Order> getOrderList() {
        ArrayList<Order> list = new ArrayList<>();
        String[] columns = {
                DBHelper.ID, DBHelper.ORDER_TIME, DBHelper.ORDER_FROM, DBHelper.ORDER_TO,
                DBHelper.ORDER_PRICE
        };

        Cursor cursor = mDatabase.query(DBHelper.ORDER_TABLE, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Order order = new Order();

                order.setTime(cursor.getString(cursor.getColumnIndex(DBHelper.ORDER_TIME)));
                order.setAddressFrom(cursor.getString(cursor.getColumnIndex(DBHelper.ORDER_FROM)));
                order.setAddressTo(cursor.getString(cursor.getColumnIndex(DBHelper.ORDER_TO)));
                order.setPrice(cursor.getString(cursor.getColumnIndex(DBHelper.ORDER_PRICE)));
                list.add(order);
            }
            while (cursor.moveToNext());
        } else {
            return null;
        }
        cursor.close();
        return list;
    }

    public ArrayList<Order> getCurrentOrderList() {
        ArrayList<Order> list = new ArrayList<>();
        String[] columns = {
                DBHelper.ID,
                DBHelper.CURRENT_ORDER_TIME,
                DBHelper.CURRENT_ORDER_FROM,
                DBHelper.CURRENT_ORDER_TO,
                DBHelper.CURRENT_ORDER_PRICE
        };

        Cursor cursor = mDatabase.query(DBHelper.CURRENT_ORDER_TABLE, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Order order = new Order();

                order.setTime(cursor.getString(cursor.getColumnIndex(DBHelper.CURRENT_ORDER_TIME)));
                order.setAddressFrom(cursor.getString(cursor.getColumnIndex(DBHelper.CURRENT_ORDER_FROM)));
                order.setAddressTo(cursor.getString(cursor.getColumnIndex(DBHelper.CURRENT_ORDER_TO)));
                order.setPrice(cursor.getString(cursor.getColumnIndex(DBHelper.CURRENT_ORDER_PRICE)));
                list.add(order);
            }
            while (cursor.moveToNext());
        } else {
            return null;
        }
        cursor.close();
        return list;
    }

    //relize

    public void insertArticlesInDataBase(ArrayList<Order> listOrders) {

        String sql = "INSERT INTO " + DBHelper.ORDER_TABLE + " VALUES (?,?,?,?,?);";
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();
        for (int i = 0; i < listOrders.size(); i++) {
            Order currentOrder = listOrders.get(i);
            statement.clearBindings();
            statement.bindString(2, currentOrder.getTime());
            statement.bindString(3, currentOrder.getAddressFrom());
            statement.bindString(4, currentOrder.getAddressTo());
            statement.bindString(5, currentOrder.getPrice());
            statement.execute();
        }
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }

    public void deleteOrderTable() {
        mDatabase.delete(DBHelper.ORDER_TABLE, null, null);
    }

    private static class DBHelper extends SQLiteOpenHelper {
        public static final String ID = "_id";
        public static final String PROFILE_TABLE = "profile";
        public static final String PROFILE_NAME = "name";
        public static final String PROFILE_PHONE = "phone";

        public static final String ORDER_TABLE = "order_table";
        public static final String ORDER_TIME = "time_order";
        public static final String ORDER_FROM = "from_order";
        public static final String ORDER_TO = "to_order";
        public static final String ORDER_PRICE = "price_order";

        public static final String CURRENT_ORDER_TABLE = "current_order_table";
        public static final String CURRENT_ORDER_TIME = "current_time_order";
        public static final String CURRENT_ORDER_FROM = "current_from_order";
        public static final String CURRENT_ORDER_TO = "current_to_order";
        public static final String CURRENT_ORDER_PRICE = "current_price_order";
        public static final String SQL_DELETE_ORDER = "DROP TABLE IF EXISTS " + ORDER_TABLE;
        public static final String SQL_DELETE_PROFILE = "DROP TABLE IF EXISTS " + PROFILE_TABLE;
        public static final String SQL_DELETE_CURRENT_ORDER = "DROP TABLE IF EXISTS " + CURRENT_ORDER_TABLE;
        private static final String TAG = "com.twohour.db";
        private static final String DATABASE_NAME = "twohour.db";
        private static final int DATABASE_VERSION = 1;
        private static final String SQL_CREATOR_CURRENT_ORDER = "CREATE TABLE " + CURRENT_ORDER_TABLE + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CURRENT_ORDER_TIME + " TEXT,"
                + CURRENT_ORDER_FROM + " TEXT,"
                + CURRENT_ORDER_TO + " TEXT,"
                + CURRENT_ORDER_PRICE + " TEXT);";
        private static final String SQL_CREATOR_ORDER = "CREATE TABLE " + ORDER_TABLE + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ORDER_TIME + " TEXT,"
                + ORDER_FROM + " TEXT,"
                + ORDER_TO + " TEXT,"
                + ORDER_PRICE + " TEXT);";
        private static final String SQL_CREATOR_PROFILE = "CREATE TABLE " + PROFILE_TABLE + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PROFILE_NAME + " TEXT,"
                + PROFILE_PHONE + " TEXT);";


        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATOR_PROFILE);
            db.execSQL(SQL_CREATOR_ORDER);
            db.execSQL(SQL_CREATOR_CURRENT_ORDER);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_PROFILE);
            db.execSQL(SQL_DELETE_ORDER);
            db.execSQL(SQL_DELETE_CURRENT_ORDER);
        }
    }
}
