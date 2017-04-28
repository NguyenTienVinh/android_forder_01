package com.framgia.forder.data.model;

import android.os.Parcel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by levutantuan on 4/27/17.
 */

public class Comment {

    @Expose
    @SerializedName("product_id")
    private int mProductId;
    @Expose
    @SerializedName("user_id")
    private int mUserId;
    @Expose
    @SerializedName("user_name")
    private String mUserName;
    @Expose
    @SerializedName("comment")
    private String mComment;
    @Expose
    @SerializedName("date")
    private String mDate;
    @SerializedName("user_avatar")
    @Expose
    private CollectionAvatar mUserImage;

    public Comment(int productId, int userId, String userName, String comment, String date) {
        mProductId = productId;
        mUserId = userId;
        mUserName = userName;
        mComment = comment;
        mDate = date;
    }

    public Comment(int productId, int userId, String comment) {
        mProductId = productId;
        mUserId = userId;
        mComment = comment;
    }

    public Comment(Parcel in) {
        mProductId = in.readInt();
        mUserId = in.readInt();
        mUserName = in.readString();
        mComment = in.readString();
        mDate = in.readString();
        mUserImage = in.readParcelable(Image.class.getClassLoader());
    }

    public int getProductId() {
        return mProductId;
    }

    public void setProductId(int productId) {
        mProductId = productId;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public CollectionAvatar getUserImage() {
        return mUserImage;
    }

    public void setUserImage(CollectionAvatar userImage) {
        mUserImage = userImage;
    }
}
