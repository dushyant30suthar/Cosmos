package in.obvious.assignments.cosmos.domain.galaxy;

import java.util.List;

import in.obvious.assignments.cosmos.domain.DomainRequestObservable;
import in.obvious.assignments.cosmos.domain.galaxy.datasources.GalaxyDatabaseDao;
import in.obvious.assignments.cosmos.domain.galaxy.datasources.GalaxyNetworkDao;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;
import retrofit2.Call;

/*
 * If any view component wants to interact with data, then it could be possible by using repository of particular
 * type of unit. For example, Both of the views needs to have information about galaxy. They both need to use
 * Galaxy Repository.*/

public class GalaxyRepository {
    private GalaxyNetworkDao galaxyNetworkDao;
    private GalaxyDatabaseDao galaxyDatabaseDao;

    public GalaxyRepository(GalaxyNetworkDao galaxyNetworkDao, GalaxyDatabaseDao galaxyDatabaseDao) {
        this.galaxyNetworkDao = galaxyNetworkDao;
        this.galaxyDatabaseDao = galaxyDatabaseDao;
    }

    /*
     * In this method we just add the actual implementation of things which are commanded by ui.*/

    public DomainRequestObservable<List<Galaxy>> getGalaxyListObservable() {

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
                return data == null;
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
