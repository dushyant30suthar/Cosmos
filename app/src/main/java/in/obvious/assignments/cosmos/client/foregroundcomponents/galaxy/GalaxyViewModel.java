package in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import in.obvious.assignments.cosmos.domain.DomainRequest;
import in.obvious.assignments.cosmos.domain.DomainRequestObserver;
import in.obvious.assignments.cosmos.domain.galaxy.GalaxyRepository;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;
import in.obvious.assignments.cosmos.framework.utils.TaskExecutors;
import io.reactivex.schedulers.Schedulers;

/*
 * Every view component has to have one viewmodel attached to it. It will store data in RAM even in orientation changes or things like that.*/
public class GalaxyViewModel extends ViewModel {

    private GalaxyRepository galaxyRepository;
    private TaskExecutors taskExecutors;

    private MutableLiveData<DomainRequest<List<Galaxy>>> galaxyListRequest;
    private MutableLiveData<Integer> galaxyId;

    public GalaxyViewModel(GalaxyRepository galaxyRepository, TaskExecutors taskExecutors) {
        this.galaxyRepository = galaxyRepository;
        this.taskExecutors = taskExecutors;
        galaxyListRequest = new MutableLiveData<>();
    }

    /*
     * Observes RxObservable and put data into the live data passed in the request.*/
    public void getGalaxyList() {

        galaxyRepository.getGalaxyListObservable()
                .subscribeOn(Schedulers.from(taskExecutors.getNetworkOperationThread()))
                .observeOn(Schedulers.from(taskExecutors.getMainThread()))
                .subscribe(new DomainRequestObserver<>(galaxyListRequest));
    }

    /*
     * Returns the live data to be observed. */
    public LiveData<DomainRequest<List<Galaxy>>> getGalaxyListRequestObservable() {
        return galaxyListRequest;
    }

    /*public LiveData<DomainRequest<Galaxy>> getGalaxyById(int id) {

    }*/
}
