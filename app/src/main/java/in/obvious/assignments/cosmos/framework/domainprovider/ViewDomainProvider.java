package in.obvious.assignments.cosmos.framework.domainprovider;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.GalaxyViewModel;
import in.obvious.assignments.cosmos.domain.galaxy.GalaxyRepository;
import in.obvious.assignments.cosmos.domain.galaxy.datasources.GalaxyDatabaseDao;
import in.obvious.assignments.cosmos.domain.galaxy.datasources.GalaxyNetworkDao;
import in.obvious.assignments.cosmos.framework.application.CosmosApplication;
import in.obvious.assignments.cosmos.framework.utils.TaskExecutors;

public class ViewDomainProvider implements ViewModelProvider.Factory {

    @Inject
    GalaxyNetworkDao galaxyNetworkDao;
    @Inject
    GalaxyDatabaseDao galaxyDatabaseDao;

    private GalaxyRepository galaxyRepository;


    /*
     * Here we prepare repositories of all of the units for example galaxy is unit, in future there can be another unit which could be used by one
     * or many ui components. This is personal preference. There are other ways in which each ui has it's own repository.
     *
     * There should not be any other way we be instanciating repositories other than this class. If there is service which needs to have repository then it also
     * must have some class which mimics viewModel for service. */
    public ViewDomainProvider() {

        CosmosApplication.getClientComponent().doInjection(this);


        this.galaxyRepository = new GalaxyRepository(galaxyNetworkDao, galaxyDatabaseDao);

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(
            @NonNull Class<T> modelClass) {
        /*
         * Here we can create various view Models.*/

        if (modelClass.isAssignableFrom(GalaxyViewModel.class)) {
            return (T) new GalaxyViewModel(galaxyRepository, TaskExecutors.getInstance());
        } else {
            throw new IllegalArgumentException("Unknown class name");
        }
    }
}