package in.obvious.assignments.cosmos.domain.galaxy;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import in.obvious.assignments.cosmos.domain.DomainRequest;
import in.obvious.assignments.cosmos.domain.DomainRequestObservable;
import in.obvious.assignments.cosmos.domain.DomainRequestObserver;
import in.obvious.assignments.cosmos.domain.galaxy.datasources.GalaxyDatabaseDao;
import in.obvious.assignments.cosmos.domain.galaxy.datasources.GalaxyNetworkDao;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;
import in.obvious.assignments.cosmos.framework.utils.TaskExecutors;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

/*
 * If any view component wants to interact with data, then it could be possible by using repository of particular
 * type of unit. For example, Both of the views needs to have information about galaxy. They both need to use
 * Galaxy Repository.*/

public class GalaxyRepository {
    private GalaxyNetworkDao galaxyNetworkDao;
    private GalaxyDatabaseDao galaxyDatabaseDao;
    private TaskExecutors taskExecutors;

    public GalaxyRepository(GalaxyNetworkDao galaxyNetworkDao, GalaxyDatabaseDao galaxyDatabaseDao, TaskExecutors taskExecutors) {
        this.galaxyNetworkDao = galaxyNetworkDao;
        this.galaxyDatabaseDao = galaxyDatabaseDao;
        this.taskExecutors = taskExecutors;
    }

    /*
     * In this method we just add the actual implementation of things which are commanded by ui.*/

    public void getGalaxyList(MutableLiveData<DomainRequest<List<Galaxy>>> galaxyListRequest) {
        getGalaxyListRequestObserver().subscribeOn(Schedulers.from(taskExecutors.getNetworkOperationThread()))
                .observeOn(Schedulers.from(taskExecutors.getMainThread()))
                .subscribe(new DomainRequestObserver<>(galaxyListRequest));
    }

    private DomainRequestObservable<List<Galaxy>> getGalaxyListRequestObserver() {
        return new DomainRequestObservable<List<Galaxy>>() {
            @Override
            protected List<Galaxy> loadFromDatabase() {
                return galaxyDatabaseDao.getGalaxyList();
            }

            @Override
            protected Call<List<Galaxy>> loadFromNetwork() {
                return galaxyNetworkDao.getGalaxyList();
            }

            /*
             * Right now we just decide that if data is already there in database then just present it from here.
             * This is not valid in production because it may change in every second so this kind of decisions are
             * made here. In production we may look for timestamp and some logic to decide whether we need to
             * make a network call or not.*/

            @Override
            protected boolean shouldFetchData(List<Galaxy> data) {
                if (data != null) {
                    return data.size() <= 0;
                }
                return true;
            }

            @Override
            protected void saveDataToDatabase(List<Galaxy> data) {
                galaxyDatabaseDao.insert(data);
            }

            @Override
            protected void clearPreviousData() {

            }

            @Override
            protected List<Galaxy> processResponse(List<Galaxy> response) {
                return null;
            }
        };
    }

}
