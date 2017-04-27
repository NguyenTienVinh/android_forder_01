package com.framgia.forder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.forder.utils.Utils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {
    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("price")
    private double mPrice;
    @Expose
    @SerializedName("description")
    private String mDescription;
    @Expose
    @SerializedName("image")
    private CollectionImage mCollectionImage;
    @Expose
    @SerializedName("start_hour")
    private String mStartHour;
    @Expose
    @SerializedName("end_hour")
    private String mEndHour;
    @Expose
    @SerializedName("status")
    private String mStatus;
    @Expose
    @SerializedName("shop")
    private Shop mShop;
    @Expose
    @SerializedName("shop_id")
    private int mShopId;

    public Product(int id, String name, double price, String description,
            CollectionImage collectionImage, String startHour, String endHour, String status,
            Shop shop, int shopId) {
        mId = id;
        mName = name;
        mPrice = price;
        mDescription = description;
        mCollectionImage = collectionImage;
        mStartHour = startHour;
        mEndHour = endHour;
        mStatus = status;
        mShop = shop;
        mShopId = shopId;
    }

    protected Product(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mPrice = in.readDouble();
        mDescription = in.readString();
        mStartHour = in.readString();
        mEndHour = in.readString();
        mStatus = in.readString();
        mShopId = in.readInt();
    }

    public int getShopId() {
        return mShopId;
    }

    public void setShopId(int shopId) {
        mShopId = shopId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public CollectionImage getCollectionImage() {
        return mCollectionImage;
    }

    public void setCollectionImage(CollectionImage collectionImage) {
        mCollectionImage = collectionImage;
    }

    public String getEndHour() {
        return mEndHour;
    }

    public void setEndHour(String endHour) {
        mEndHour = endHour;
    }

    public String getStartHour() {
        return mStartHour;
    }

    public void setStartHour(String startHour) {
        mStartHour = startHour;
    }

    public Shop getShop() {
        return mShop;
    }

    public void setShop(Shop shop) {
        mShop = shop;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getFormatStartHour() {
        return Utils.DateTimeUntils.convertUiFormatToDataFormat(mStartHour, Utils.INPUT_TIME_FORMAT,
                Utils.OUTPUT_TIME_FORMAT);
    }

    public String getFormatEndHour() {
        return Utils.DateTimeUntils.convertUiFormatToDataFormat(mEndHour, Utils.INPUT_TIME_FORMAT,
                Utils.OUTPUT_TIME_FORMAT);
    }

    public String getFormatPrice() {
        return String.format(Utils.FORMAT_PRICE, mPrice);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeDouble(mPrice);
        dest.writeString(mDescription);
        dest.writeString(mStartHour);
        dest.writeString(mEndHour);
        dest.writeString(mStatus);
        dest.writeInt(mShopId);
    }
}
