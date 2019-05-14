package project.akshay.furnituremagiktask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseManager implements BaseColumns {

    private static final int A_VERSION = 1;
    private static final String databaseName = "PRODUCTS_DATA";
    private static final String k_id = BaseColumns._ID;
    private static final String tableName = "PRODUCTS_TABLE";
    private static final String k_name = "NAME";
    private static final String k_quantity = "QUANTITY";
    private static final String k_price = "PRICE";
    Context context;

    String CREATE_TABLE = "CREATE TABLE " + tableName + " (" + k_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            k_name + " TEXT, " + k_quantity + " TEXT, " + k_price + " TEXT);";

    public DatabaseManager(Context context) {
        this.context = context;
    }

    DataBase dataBase;

    public class DataBase extends SQLiteOpenHelper {

        public DataBase(Context context) {
            super(context, databaseName, null, A_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
            onCreate(db);
        }
    }

    public void addProduct(Product product) {

        dataBase = new DataBase(context);
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(k_name, product.getProductName());
        contentValues.put(k_quantity, product.getQuantity());
        contentValues.put(k_price, product.getTotalPrice());

        sqLiteDatabase.insert(tableName,null,contentValues);
        sqLiteDatabase.close();

    }

    public ArrayList<Product> getAllProducts() {

        DataBase dataBase = new DataBase(context);
        ArrayList<Product> productsList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM " + tableName + " ORDER BY " + k_name + " COLLATE NOCASE ASC";

        SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery,null);

        if(cursor.moveToFirst()){
            do {
                Product product = new Product(cursor.getString(1),cursor.getString(2),"",
                        cursor.getString(3),cursor.getInt(0));
                productsList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return productsList;

    }

}
