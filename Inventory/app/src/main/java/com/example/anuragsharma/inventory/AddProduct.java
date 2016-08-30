package com.example.anuragsharma.inventory;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by anuragsharma on 24/08/16.
 */
public class AddProduct extends AppCompatActivity {
    private static final String LOG_TAG = AddProduct.class.getSimpleName();
    private int quantity = 1;
    private int PICK_IMAGE_REQUEST = 1;
    private String mImageLink;
    private Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_activity);

        Button addImageButton = (Button) findViewById(R.id.btnAddImage);
        Button addProductButton = (Button) findViewById(R.id.btnAddProduct);

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                if (Build.VERSION.SDK_INT < 19) {
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                } else {
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                }

                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                Log.v("AddProductActivity", "get image");
                // Then define onActivityResult to do something with picked image
            }
        });

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInput();
            }
        });

        Button btnMinusQuantity = (Button) findViewById(R.id.btnMinusQuantity);
        Button btnPlusQuantity = (Button) findViewById(R.id.btnPlusQuantity);
        final TextView quantityTV = (TextView) findViewById(R.id.quantityTextView);
        quantityTV.setText(String.format(Locale.ENGLISH, "%d", quantity));

        btnMinusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity > 0) {
                    quantity -= 1;
                    quantityTV.setText(String.format(Locale.ENGLISH, "%d", quantity));
                } else {
                    Toast.makeText(AddProduct.this, "Minimum Quantity is 0", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPlusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity += 1;
                quantityTV.setText(String.format(Locale.ENGLISH, "%d", quantity));
            }
        });
    }

    protected void checkInput(){
        EditText edtProductName = (EditText) findViewById(R.id.editProductName);
        EditText edtPrice = (EditText) findViewById(R.id.edtPrice);
        EditText edtSeller = (EditText) findViewById(R.id.editSeller);
        int count=0;
        if(edtProductName.getText().toString().isEmpty())
            count++;
        if(edtPrice.getText().toString().isEmpty())
            count++;
        if(edtSeller.getText().toString().isEmpty())
            count++;

        if(count>0) {
            Toast.makeText(AddProduct.this, "Please fill the required field", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (mImageLink == null) {
            Toast toast = Toast.makeText(AddProduct.this, "Please select a picture of the product from gallery.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        else{
            InventoryDbHelper db = new InventoryDbHelper(this);
            String name = edtProductName.getText().toString();
            int price = Integer.parseInt(edtPrice.getText().toString());
            String seller = edtSeller.getText().toString();
            mProduct = new Product(name, price, quantity, mImageLink, seller);
            db.insertData(mProduct);
        }
        setResult(RESULT_OK, new Intent());
        finish();
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data) {

        Log.v(LOG_TAG, "received image data");

        if (resCode == RESULT_OK && reqCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            Log.v(LOG_TAG, "received image uri");
            //The uri information is stored in every product item.
            mImageLink = selectedImageUri.toString();
        }
        super.onActivityResult(reqCode, resCode, data);
    }

}
