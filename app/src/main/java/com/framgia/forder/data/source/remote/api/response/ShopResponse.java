package com.framgia.forder.data.source.remote.api.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.forder.data.model.Shop;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ShopResponse implements Parcelable {
    public static final Creator<ShopResponse> CREATOR = new Creator<ShopResponse>() {
        @Override
        public ShopResponse createFromParcel(Parcel in) {
            return new ShopResponse(in);
        }

        @Override
        public ShopResponse[] newArray(int size) {
            return new ShopResponse[size];
        }
    };

    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("content")
    private List<Shop> mListShop;

    protected ShopResponse(Parcel in) {
        mStatus = in.readInt();
        mMessage = in.readString();
        mListShop = in.createTypedArrayList(Shop.CREATOR);
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<Shop> getListShop() {
        return mListShop;
    }

    public void setListShop(List<Shop> listShop) {
        mListShop = listShop;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mStatus);
        dest.writeString(mMessage);
        dest.writeTypedList(mListShop);
    }
}
