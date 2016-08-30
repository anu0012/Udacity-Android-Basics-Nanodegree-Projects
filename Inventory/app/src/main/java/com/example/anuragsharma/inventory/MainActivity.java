package com.example.anuragsharma.inventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addProductButton;
    private InventoryDbHelper mDbHelper = new InventoryDbHelper(this);
    private TextView mDisplayMsg;
    private ListView mListView;
    private ArrayList<Product> mProductsList = new ArrayList<>();
    ProductAdapter mAdapter;
    private static final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addProductButton = (Button) findViewById(R.id.add_item_button);
        mListView = (ListView)findViewById(R.id.list);
        mAdapter = new ProductAdapter(MainActivity.this, mProductsList);
        mListView.setAdapter(mAdapter);
        mDisplayMsg = (TextView) findViewById(R.id.display_message);

        if (mDbHelper.getProductCount() == 0) {
            mDisplayMsg.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        } else {
            mListView.setVisibility(View.VISIBLE);
            mDisplayMsg.setVisibility(View.GONE);
        }

        mProductsList.addAll(mDbHelper.queryAllEntries());
        mAdapter.notifyDataSetChanged();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Product item = mAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                intent.putExtra("item_info", item);
                startActivityForResult(intent, 0);
            }
        });

        Button btnAddProduct = (Button) findViewById(R.id.add_item_button);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddProduct.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mListView.getVisibility() == View.GONE) {
            mListView.setVisibility(View.VISIBLE);
            mDisplayMsg.setVisibility(View.GONE);
        }

        mAdapter.clear();
        mProductsList.clear();
        mProductsList.addAll(mDbHelper.queryAllEntries());
        Log.v(LOG_TAG, "returning from add product activity.");
        for (Product p : mProductsList) {
            Log.v(LOG_TAG, p.getName());
        }

        mAdapter.notifyDataSetChanged();

        if (mProductsList != null) {
            if (mProductsList.size() == 0) {
                mListView.setVisibility(View.GONE);
            } else {
                mListView.setVisibility(View.VISIBLE);
                mDisplayMsg.setVisibility(View.GONE);
            }
        } else {
            mListView.setVisibility(View.GONE);
        }
    }

}
