package com.framgia.forder.data.source.remote.api.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.forder.data.model.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ProductResponse implements Parcelable {
    public static final Creator<ProductResponse> CREATOR = new Creator<ProductResponse>() {
        @Override
        public ProductResponse createFromParcel(Parcel in) {
            return new ProductResponse(in);
        }

        @Override
        public ProductResponse[] newArray(int size) {
            return new ProductResponse[size];
        }
    };

    @Expose
    @SerializedName("content")
    private List<Product> mListProduct;
    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;

    protected ProductResponse(Parcel in) {
        mListProduct = in.createTypedArrayList(Product.CREATOR);
        mStatus = in.readInt();
        mMessage = in.readString();
    }

    public List<Product> getListProduct() {
        return mListProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        mListProduct = listProduct;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mListProduct);
        dest.writeInt(mStatus);
        dest.writeString(mMessage);
    }
}
