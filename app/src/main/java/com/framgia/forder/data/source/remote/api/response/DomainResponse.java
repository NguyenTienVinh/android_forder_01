package com.framgia.forder.data.source.remote.api.response;

import com.framgia.forder.data.model.Domain;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Age on 4/13/2017.
 */

public class DomainResponse extends BaseRespone {
    @SerializedName("status")
    @Expose
    private Integer mStatus;
    @SerializedName("message")
    @Expose
    private String mMessage;
    @SerializedName("content")
    @Expose
    private List<ChooseDomain> mDomainList;

    public Integer getStatus() {
        return mStatus;
    }

    public void setStatus(Integer status) {
        mStatus = status;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<ChooseDomain> getDomainList() {
        return mDomainList;
    }

    public void setDomainList(List<ChooseDomain> domainList) {
        mDomainList = domainList;
    }

    public static class ChooseDomain {
        @SerializedName("domain")
        @Expose
        private Domain mDomain;

        public Domain getDomain() {
            return mDomain;
        }

        public void setDomain(Domain domain) {
            mDomain = domain;
        }
    }
}
