package com.framgia.forder.data.source.local.realm;

/**
 * Created by le.quang.dao on 13/03/2017.
 */

public interface BaseLocalDataSource {
    void openTransaction();

    void closeTransaction();
}
