package com.framgia.forder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Age on 4/12/2017.
 */

public class Domain implements Parcelable {
    @Expose
    @SerializedName("id")
    private Integer mId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("created_at")
    private String mCreatedAt;
    @Expose
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @Expose
    @SerializedName("slug")
    private String mSlug;
    @Expose
    @SerializedName("status")
    private String mStatus;
    @Expose
    @SerializedName("owner")
    private Integer mOwner;

    public Domain(Integer id, String name, String createdAt, String updatedAt, String slug,
            String status, Integer owner) {
        mId = id;
        mName = name;
        mCreatedAt = createdAt;
        mUpdatedAt = updatedAt;
        mSlug = slug;
        mStatus = status;
        mOwner = owner;
    }

    protected Domain(Parcel in) {
        mName = in.readString();
        mCreatedAt = in.readString();
        mUpdatedAt = in.readString();
        mSlug = in.readString();
        mStatus = in.readString();
    }

    public static final Creator<Domain> CREATOR = new Creator<Domain>() {
        @Override
        public Domain createFromParcel(Parcel in) {
            return new Domain(in);
        }

        @Override
        public Domain[] newArray(int size) {
            return new Domain[size];
        }
    };

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getSlug() {
        return mSlug;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public Integer getOwner() {
        return mOwner;
    }

    public void setOwner(Integer owner) {
        mOwner = owner;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mCreatedAt);
        dest.writeString(mUpdatedAt);
        dest.writeString(mSlug);
        dest.writeString(mStatus);
    }
}
