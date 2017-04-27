package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.model.User;
import java.util.List;
import rx.Observable;

/**
 * Created by Age on 4/12/2017.
 */

public class DomainDataSource {
    public interface LocalDataSource {
        User getUser();

        void saveCurrentDomain(Domain domain);

        Domain getCurrentDomain();
    }

    public interface RemoteDataSource {
        Observable<List<Domain>> getListDomain(int userId, String userEmail, String userToken);
    }
}
