package in.obvious.assignments.cosmos.domain.galaxy;

import java.util.List;

import in.obvious.assignments.cosmos.domain.DataFlowControl;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;
import in.obvious.assignments.cosmos.framework.database.DatabaseModule;
import in.obvious.assignments.cosmos.framework.network.NetworkModule;
import retrofit2.Call;

public class GalaxyRepository {
    private NetworkModule networkModule;
    private DatabaseModule databaseModule;

    public GalaxyRepository(NetworkModule networkModule, DatabaseModule databaseModule) {
        this.networkModule = networkModule;
        this.databaseModule = databaseModule;
    }

    public void getGalaxyList() {
        new DataFlowControl<List<Galaxy>>() {
            @Override
            protected List<Galaxy> loadFromDatabase() {
                return null;
            }

            @Override
            protected Call<List<Galaxy>> loadFromNetwork() {
                return null;
            }

            @Override
            protected boolean shouldFetchData(List<Galaxy> data) {
                return false;
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
