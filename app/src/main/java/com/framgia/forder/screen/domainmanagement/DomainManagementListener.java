package com.framgia.forder.screen.domainmanagement;

import com.framgia.forder.data.model.DomainManagement;

/**
 * Created by Age on 6/8/2017.
 */

public interface DomainManagementListener {
    void onGetListUserInDomain(DomainManagement domainManagement);

    void onGetListShopInDomain(DomainManagement domainManagement);

    void onLeaveDomain(int domainId);

    void onEditDomain(int domainId);

    void onDeleteDomain(int domainId);
}
