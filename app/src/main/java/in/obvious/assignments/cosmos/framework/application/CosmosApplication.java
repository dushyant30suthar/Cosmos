package in.obvious.assignments.cosmos.framework.application;

import android.app.Application;

import in.obvious.assignments.cosmos.framework.dagger.ClientComponent;
import in.obvious.assignments.cosmos.framework.dagger.DaggerClientComponent;
import in.obvious.assignments.cosmos.framework.dagger.DaggerViewModelComponent;
import in.obvious.assignments.cosmos.framework.dagger.ViewModelComponent;
import in.obvious.assignments.cosmos.framework.database.DatabaseModule;
import in.obvious.assignments.cosmos.framework.network.NetworkModule;

public class CosmosApplication extends Application {

    protected static ClientComponent clientComponent;

    protected static ViewModelComponent viewModelComponent;

    public static ClientComponent getClientComponent() {
        return clientComponent;
    }

    public static ViewModelComponent getViewModelComponent() {
        return viewModelComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setDataLayerComponent();
        setViewModelComponent();
    }

    protected void setDataLayerComponent() {
        clientComponent = DaggerClientComponent.builder()
                .networkModule(new NetworkModule(this))
                .databaseModule(new DatabaseModule(this))
                .build();
    }

    private void setViewModelComponent() {
        viewModelComponent = DaggerViewModelComponent.builder()
                .applicationModule(new ApplicationModule())
                .build();
    }
}
