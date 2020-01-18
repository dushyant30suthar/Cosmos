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
     * In this method we just implement the actual implementation of things which are commanded by ui.*/

    public DomainRequestObservable<List<Galaxy>> getGalaxyListObservable() {

        return new DomainRequestObservable<List<Galaxy>>() {
            @Override
            protected List<Galaxy> loadFromDatabase() {
                return null;
            }

            @Override
            protected Call<List<Galaxy>> loadFromNetwork() {
                return galaxyNetworkDao.getGalaxyList();
            }

            @Override
            protected boolean shouldFetchData(List<Galaxy> data) {
                return true;
            }

            @Override
            protected void saveDataToDatabase(List<Galaxy> data) {

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
