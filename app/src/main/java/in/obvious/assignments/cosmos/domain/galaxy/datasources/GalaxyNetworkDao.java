package in.obvious.assignments.cosmos.domain.galaxy.datasources;


import java.util.List;

import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GalaxyNetworkDao {

    @GET("b/5fc20e0f045eb86f1f88241a")
    Call<List<Galaxy>> getGalaxyList();

}
