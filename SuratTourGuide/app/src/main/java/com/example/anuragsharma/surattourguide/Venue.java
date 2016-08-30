package com.example.anuragsharma.surattourguide;

/**
 * Created by anuragsharma on 08/08/16.
 */
public class Venue {

    private String mLocation;
    private String mAddress;
    private int mresourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    // Constructor with only Location and Address
    // No Image
    public Venue(String location, String address){
        mLocation = location;
        mAddress = address;
    }

    //Constructor with Location, Address and Photo
    public Venue(String location, String address, int resourceId){
        mLocation = location;
        mAddress = address;
        mresourceId = resourceId;
    }

    // Method for Getting Location
    public String getLocation(){
        return mLocation;
    }

    // Method for Getting Address
    public String getAddress(){
        return mAddress;
    }

    // Method for Getting ImageID
    public int getResourceId(){
        return mresourceId;
    }

    //Check whether it has Image or not
    public boolean hasImage() {
        return mresourceId != NO_IMAGE_PROVIDED;
    }
}
