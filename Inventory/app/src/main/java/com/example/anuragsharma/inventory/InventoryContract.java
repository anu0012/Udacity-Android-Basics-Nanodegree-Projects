package com.example.anuragsharma.inventory;

import android.provider.BaseColumns;

/**
 * Created by anuragsharma on 24/08/16.
 */
public class InventoryContract {

    public InventoryContract(){}

    public static final String DATABASE_NAME = "Inventory.db";
    public static final int DATABASE_VERSION = 1;

    public class InventoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "Products";
        public static final String _ID = "_id";
        public static final String COLUMN_NAME = "product";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_SELLER = "seller";
        public static final String COLUMN_QUANTITY = "quantity";
    }
}
