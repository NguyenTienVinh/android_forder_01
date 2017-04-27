package com.framgia.forder.data.source.remote.api.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Age on 4/20/2017.
 */

public class SearchResponse implements Parcelable {
    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("content_shop")
    private List<Shop> mListShop;
    @Expose
    @SerializedName("content_product")
    private List<Product> mListProduct;

    public SearchResponse() {
    }

    public SearchResponse(int status, String message, List<Shop> listShop,
            List<Product> listProduct) {
        mStatus = status;
        mMessage = message;
        mListShop = listShop;
        mListProduct = listProduct;
    }

    protected SearchResponse(Parcel in) {
        mStatus = in.readInt();
        mMessage = in.readString();
        mListShop = in.createTypedArrayList(Shop.CREATOR);
        mListProduct = in.createTypedArrayList(Product.CREATOR);
    }

    public static final Creator<SearchResponse> CREATOR = new Creator<SearchResponse>() {
        @Override
        public SearchResponse createFromParcel(Parcel in) {
            return new SearchResponse(in);
        }

        @Override
        public SearchResponse[] newArray(int size) {
            return new SearchResponse[size];
        }
    };

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

    public List<Product> getListProduct() {
        return mListProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        mListProduct = listProduct;
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
        dest.writeTypedList(mListProduct);
    }
}
