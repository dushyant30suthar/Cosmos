package in.obvious.assignments.cosmos.domain.galaxy.datasources;


import java.util.List;

import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GalaxyNetworkDao {

    @GET("bins/1f3vm2")
    Call<List<Galaxy>> getGalaxyList();

}
