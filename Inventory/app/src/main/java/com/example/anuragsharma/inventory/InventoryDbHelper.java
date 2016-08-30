package com.example.anuragsharma.inventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by anuragsharma on 24/08/16.
 */
public class InventoryDbHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_TABLE_PRODUCTS =
            "CREATE TABLE " + InventoryContract.InventoryEntry.TABLE_NAME + " (" +
                    InventoryContract.InventoryEntry._ID + " INTEGER PRIMARY KEY, " +
                    InventoryContract.InventoryEntry.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    InventoryContract.InventoryEntry.COLUMN_PRICE + INTEGER_TYPE + COMMA_SEP +
                    InventoryContract.InventoryEntry.COLUMN_QUANTITY + INTEGER_TYPE + COMMA_SEP +
                    InventoryContract.InventoryEntry.COLUMN_SELLER + TEXT_TYPE + COMMA_SEP +
                    InventoryContract.InventoryEntry.COLUMN_IMAGE + TEXT_TYPE +
                    " )";
    public InventoryDbHelper(Context context) {
        super(context, InventoryContract.DATABASE_NAME, null, InventoryContract.DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_PRODUCTS);
    }

    public void insertData(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(InventoryContract.InventoryEntry.COLUMN_NAME, product.getName());
        values.put(InventoryContract.InventoryEntry.COLUMN_PRICE, product.getPrice());
        values.put(InventoryContract.InventoryEntry.COLUMN_QUANTITY, product.getQuantity());
        values.put(InventoryContract.InventoryEntry.COLUMN_SELLER, product.getSeller());
        values.put(InventoryContract.InventoryEntry.COLUMN_IMAGE, product.getPhoto());
        db.insert(InventoryContract.InventoryEntry.TABLE_NAME, null, values);
        db.close();
    }

    //This method is used to modify the product quantity based on sold number.
    public void updateProductQuantityAfterSelling(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryEntry.COLUMN_QUANTITY, product.getQuantity() - 1);

        db.update(InventoryContract.InventoryEntry.TABLE_NAME, values, InventoryContract.InventoryEntry.COLUMN_NAME + " =?",
                new String[]{String.valueOf(product.getName())});
        db.close();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + InventoryContract.InventoryEntry.TABLE_NAME);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    //This method is used to query all the products from table.
    public ArrayList<Product> queryAllEntries() {
        ArrayList<Product> productsList = new ArrayList<>();
        final String SQL_QUERY_ALL_ITEMS = "SELECT * FROM " + InventoryContract.InventoryEntry.TABLE_NAME + ";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL_QUERY_ALL_ITEMS, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product(cursor.getString(1), cursor.getInt(2), cursor.getInt(3),
                         cursor.getString(5), cursor.getString(4));
                productsList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return productsList;
    }

    public void deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(InventoryContract.InventoryEntry.TABLE_NAME, InventoryContract.InventoryEntry.COLUMN_NAME + " =?",
                new String[]{String.valueOf(product.getName())});
        db.close();
    }

    //This method is used to delete the whole table.
    public void deleteDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + InventoryContract.InventoryEntry.TABLE_NAME + ";");
        db.close();
    }

    //This method is used to get count of products in the database.
    public int getProductCount() {
        String countQuery = "SELECT * FROM " + InventoryContract.InventoryEntry.TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

}
