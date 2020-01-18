package in.obvious.assignments.cosmos.framework.application;

import dagger.Module;
import dagger.Provides;
import in.obvious.assignments.cosmos.framework.domainprovider.ServiceDomainProvider;
import in.obvious.assignments.cosmos.framework.domainprovider.ViewDomainProvider;

/*
 * This module provides factory class of viewmodel to create view model out of it as per screen.*/
@Module
public class ApplicationModule {

    @Provides
    public ViewDomainProvider getViewModelFactory() {
        return new ViewDomainProvider();
    }

    @Provides
    public ServiceDomainProvider getServiceModelFactory() {
        return new ServiceDomainProvider();
    }


}