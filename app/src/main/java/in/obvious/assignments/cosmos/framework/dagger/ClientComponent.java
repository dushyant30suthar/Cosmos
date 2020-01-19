package in.obvious.assignments.cosmos.framework.dagger;

import dagger.Component;
import in.obvious.assignments.cosmos.framework.database.DatabaseModule;
import in.obvious.assignments.cosmos.framework.network.NetworkModule;
import in.obvious.assignments.cosmos.framework.viewmodelfactory.ServiceDomainProvider;
import in.obvious.assignments.cosmos.framework.viewmodelfactory.ViewModelFactory;

/*
 * Dagger component which is expected to provide network and database clients to viewModel factories.*/
@Component(modules = {NetworkModule.class, DatabaseModule.class})
public interface ClientComponent {

    void doInjection(ViewModelFactory viewModelFactory);

    void doInjection(ServiceDomainProvider serviceDomainProvider);
}

