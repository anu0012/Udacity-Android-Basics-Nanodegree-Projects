package com.example.anuragsharma.inventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by anuragsharma on 24/08/16.
 */
public class ProductAdapter extends ArrayAdapter<Product> {
    private InventoryDbHelper mDbHelper = new InventoryDbHelper(getContext());
    public ProductAdapter(Context context,ArrayList<Product> products){
        super(context,0,products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        final Product currentProduct = getItem(position);

        TextView nameView = (TextView) listView.findViewById(R.id.item_name);
        nameView.setText(currentProduct.getName());

        TextView priceView = (TextView) listView.findViewById(R.id.item_price);
        priceView.setText("Price:\n"+currentProduct.getPrice());

        TextView quantityView = (TextView) listView.findViewById(R.id.item_quantity);
        quantityView.setText("Quantity:\n"+currentProduct.getQuantity()+"");

        Button buttonSold = (Button)listView.findViewById(R.id.button_sold);
        buttonSold.setFocusable(false);
        buttonSold.setFocusableInTouchMode(false);
        buttonSold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentProduct.getQuantity() == 0) {
                    return;
                }
                mDbHelper.updateProductQuantityAfterSelling(currentProduct);
                currentProduct.setQuantity(currentProduct.getQuantity() - 1);
                notifyDataSetChanged();
            }
        });

        return listView;
    }
}
