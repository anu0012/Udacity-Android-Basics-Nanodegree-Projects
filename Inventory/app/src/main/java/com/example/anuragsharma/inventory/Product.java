package com.example.anuragsharma.inventory;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anuragsharma on 24/08/16.
 */
public class Product implements Parcelable {
    private int mId;
    private String mProductName;
    private int mPrice;
    private int mQuantity;
    private String mSeller;
    private String mImageResource;

    public Product(int Id,String name,int quantity,int price,String seller){
        mId = Id;
        mProductName = name;
        mPrice = price;
        mQuantity = quantity;
        mSeller = seller;
    }

    public Product(String name, int price, int currentQuantity, String imageLink,
                   String supplierEmail) {
        mProductName = name;
        mPrice = price;
        mQuantity = currentQuantity;
        mImageResource = imageLink;
        mSeller = supplierEmail;
    }

    public void setImageResource(String imageResource) {
        mImageResource = imageResource;
    }

    public int getId() {return mId;}

    public String getName(){ return mProductName;}

    public int getPrice(){return mPrice;}

    public String getPhoto(){ return mImageResource;}

    public String getSeller() {return mSeller;}

    public int getQuantity(){return mQuantity;}

    public void setName(String mName) {
        this.mProductName = mName;
    }

    public void setPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public void setQuantity(int mCurrentQuantity) {
        this.mQuantity = mCurrentQuantity;
    }

    public void setImageLink(String mImageLink) {
        this.mImageResource = mImageLink;
    }

    public void setSupplierEmail(String mSupplierEmail) {
        this.mSeller = mSupplierEmail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source.readString(), source.readInt(),
                    source.readInt(), source.readString(), source.readString());
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mProductName);
        parcel.writeInt(mPrice);
        parcel.writeInt(mQuantity);
        parcel.writeString(mImageResource);
        parcel.writeString(mSeller);
    }
}
