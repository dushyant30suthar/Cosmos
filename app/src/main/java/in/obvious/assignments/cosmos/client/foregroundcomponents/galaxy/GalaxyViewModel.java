package in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import in.obvious.assignments.cosmos.domain.DomainRequest;
import in.obvious.assignments.cosmos.domain.galaxy.GalaxyRepository;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;

/*
 * Every view component has to have one viewmodel attached to it. It will store data in RAM even in orientation changes or things like that.*/
public class GalaxyViewModel extends ViewModel {

    private GalaxyRepository galaxyRepository;

    private MutableLiveData<DomainRequest<List<Galaxy>>> galaxyListRequest;
    private MutableLiveData<Integer> galaxyId;

    /*
     * We can make use of any number of repositories if our ui demands. For example, if we there a view int this component which shows User information
     * then we would make use of UserRepository to perform tasks*/
    public GalaxyViewModel(GalaxyRepository galaxyRepository) {
        this.galaxyRepository = galaxyRepository;
        galaxyListRequest = new MutableLiveData<>();
    }

    /*
     * Observes RxObservable and put data into the live data passed in the request.*/
    public void getGalaxyList() {
        galaxyRepository.getGalaxyList(galaxyListRequest);
    }

    /*
     * Returns the live data to be observed. */
    public LiveData<DomainRequest<List<Galaxy>>> getGalaxyListRequestObservable() {
        return galaxyListRequest;
    }

    /*public LiveData<DomainRequest<Galaxy>> getGalaxyById(int id) {

    }*/
}
