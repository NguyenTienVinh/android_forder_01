package com.framgia.forder.data.source.remote.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by levutantuan on 4/27/17.
 */

public class CommentRequest extends BaseRequest {
    @Expose
    @SerializedName("product_id")
    private int mProductId;
    @Expose
    @SerializedName("user_id")
    private int mUserId;
    @Expose
    @SerializedName("comment")
    private String mComment;

    public CommentRequest(int productId, int userId, String comment) {
        mProductId = productId;
        mUserId = userId;
        mComment = comment;
    }

    public CommentRequest() {
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

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }
}
