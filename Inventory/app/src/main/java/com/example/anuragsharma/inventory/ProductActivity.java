package com.example.anuragsharma.inventory;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Created by anuragsharma on 24/08/16.
 */
public class ProductActivity extends AppCompatActivity {
    private static final String LOG_TAG = ProductActivity.class.getSimpleName();
    SQLiteDatabase db;
    private InventoryDbHelper mDbHelper = new InventoryDbHelper(this);
    private Product mProduct;
    private TextView nameDetail;
    private TextView priceDetail;
    private TextView currentQuantityDetail;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_activity);
        InventoryDbHelper db_helper = new InventoryDbHelper(getApplicationContext());
        db = db_helper.getWritableDatabase();
        Intent intent = getIntent();

        mProduct = intent.getParcelableExtra("item_info");
        // Load Data for the provided id
        nameDetail = (TextView) findViewById(R.id.detail_product_name);
        currentQuantityDetail = (TextView) findViewById(R.id.detail_product_qty);
        priceDetail = (TextView) findViewById(R.id.detail_product_price);
        imageView = (ImageView) findViewById(R.id.pic);
        display(mProduct);

        Button btnDelete = (Button) findViewById(R.id.btnDetailDelete);
        Button btnSell = (Button) findViewById(R.id.sell_button);
        Button btnOrder = (Button) findViewById(R.id.btnDetailOrder);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDbHelper.deleteProduct(mProduct);
                setResult(RESULT_OK, new Intent(ProductActivity.this, MainActivity.class));
                finish();
            }
        });

        btnSell.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mProduct.getQuantity() == 0) {
                    return;
                }
                mDbHelper.updateProductQuantityAfterSelling(mProduct);
                mProduct.setQuantity(mProduct.getQuantity() - 1);
                display(mProduct);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "Hi, I want to order (number) " + mProduct.getName() +" from your shop.\n Thank you!";
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + mProduct.getSeller()));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "PRODUCTS ORDER");
                emailIntent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(emailIntent);
            }
        });
    }

    private void display(Product product) {
        //Display text information.
        nameDetail.setText(product.getName());
        priceDetail.setText("Prize:  "+String.valueOf(product.getPrice()));
        currentQuantityDetail.setText("Quantity: "+String.valueOf(product.getQuantity()));

        //Display product image.
        if(product.getPhoto()!=null) {
            Bitmap selectedImage = getBitmapFromUri(Uri.parse(product.getPhoto()));
            //Sometimes the bitmap image would be too large, so it needs to be scaled down.
            if (selectedImage != null) {
                int nh = (int) (selectedImage.getHeight() * (512.0 / selectedImage.getWidth()));
                Bitmap scaledImage = Bitmap.createScaledBitmap(selectedImage, 512, nh, true);
                imageView.setImageBitmap(scaledImage);
            }
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) {
        ParcelFileDescriptor parcelFileDescriptor = null;
        try {
            parcelFileDescriptor = this.getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();
            return image;
        } catch (Exception e) {
            Log.v(LOG_TAG, "Failed to load image.", e);
            return null;
        } finally {
            try {
                if (parcelFileDescriptor != null) {
                    parcelFileDescriptor.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.v(LOG_TAG, "Error closing ParcelFile Descriptor");
            }
        }
    }
}
