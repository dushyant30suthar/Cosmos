package in.obvious.assignments.cosmos.framework.application;

import dagger.Module;
import dagger.Provides;
import in.obvious.assignments.cosmos.framework.viewmodelfactory.ServiceDomainProvider;
import in.obvious.assignments.cosmos.framework.viewmodelfactory.ViewModelFactory;

/*
 * This module provides factory class of viewmodel to create view model out of it as per screen.*/
@Module
public class ApplicationModule {

    @Provides
    public ViewModelFactory getViewModelFactory() {
        return new ViewModelFactory();
    }

    @Provides
    public ServiceDomainProvider getServiceModelFactory() {
        return new ServiceDomainProvider();
    }


}