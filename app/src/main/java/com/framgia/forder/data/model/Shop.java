package com.framgia.forder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shop implements Parcelable {

    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel in) {
            return new Shop(in);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };

    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("domain_id")
    private int mDomainId;
    @Expose
    @SerializedName("shop_id")
    private int mShopId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("description")
    private String mDescription;
    @Expose
    @SerializedName("status")
    private String mStatus;
    @Expose
    @SerializedName("avatar")
    private CollectionAvatar mCollectionAvatar;
    @Expose
    @SerializedName("averate_rating")
    private float mAverageRating;
    @Expose
    @SerializedName("owner_id")
    private int mOwnerId;
    @Expose
    @SerializedName("user")
    private User mUser;
    @Expose
    @SerializedName("time_auto_reject")
    private String mTimeAutoReject;

    protected Shop(Parcel in) {
        mId = in.readInt();
        mDomainId = in.readInt();
        mShopId = in.readInt();
        mName = in.readString();
        mDescription = in.readString();
        mStatus = in.readString();
        mCollectionAvatar = in.readParcelable(CollectionAvatar.class.getClassLoader());
        mAverageRating = in.readFloat();
        mOwnerId = in.readInt();
        mUser = in.readParcelable(User.class.getClassLoader());
        mTimeAutoReject = in.readString();
    }

    public Shop(int id, int domainId, int shopId, String name, String description, String status,
            CollectionAvatar collectionAvatar, float averageRating, int ownerId, User user,
            String timeAutoReject) {
        mId = id;
        mDomainId = domainId;
        mShopId = shopId;
        mName = name;
        mDescription = description;
        mStatus = status;
        mCollectionAvatar = collectionAvatar;
        mAverageRating = averageRating;
        mOwnerId = ownerId;
        mUser = user;
        mTimeAutoReject = timeAutoReject;
    }

    public int getDomainId() {
        return mDomainId;
    }

    public void setDomainId(int domainId) {
        mDomainId = domainId;
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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public float getAverageRating() {
        return mAverageRating;
    }

    public void setAverageRating(float averageRating) {
        mAverageRating = averageRating;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public int getOwnerId() {
        return mOwnerId;
    }

    public void setOwnerId(int ownerId) {
        mOwnerId = ownerId;
    }

    public String getTimeAutoReject() {
        return mTimeAutoReject;
    }

    public void setTimeAutoReject(String timeAutoReject) {
        mTimeAutoReject = timeAutoReject;
    }

    public CollectionAvatar getCollectionAvatar() {
        return mCollectionAvatar;
    }

    public void setCollectionAvatar(CollectionAvatar collectionAvatar) {
        mCollectionAvatar = collectionAvatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeInt(mDomainId);
        dest.writeInt(mShopId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeString(mStatus);
        dest.writeParcelable(mCollectionAvatar, flags);
        dest.writeFloat(mAverageRating);
        dest.writeInt(mOwnerId);
        dest.writeParcelable(mUser, flags);
        dest.writeString(mTimeAutoReject);
    }
}
