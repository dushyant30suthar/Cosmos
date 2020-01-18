package in.obvious.assignments.cosmos.framework.dagger;

import dagger.Component;
import in.obvious.assignments.cosmos.framework.database.DatabaseModule;
import in.obvious.assignments.cosmos.framework.domainprovider.ServiceDomainProvider;
import in.obvious.assignments.cosmos.framework.domainprovider.ViewDomainProvider;
import in.obvious.assignments.cosmos.framework.network.NetworkModule;

/*
 * Dagger component which is expected to provide network and database clients to viewModel providers.*/
@Component(modules = {NetworkModule.class, DatabaseModule.class})
public interface ClientComponent {

    void doInjection(ViewDomainProvider viewDomainProvider);

    void doInjection(ServiceDomainProvider serviceDomainProvider);
}

