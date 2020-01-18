package in.obvious.assignments.cosmos.framework.dagger;

import dagger.Component;
import in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.detailscomponent.GalaxyDetailFragment;
import in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.gridcomponent.GalaxyGridFragment;
import in.obvious.assignments.cosmos.framework.application.ApplicationModule;

@Component(
        modules = {ApplicationModule.class})
public interface ViewModelComponent {

    void doInjection(GalaxyGridFragment galaxyGridFragment);

    void doInjection(GalaxyDetailFragment galaxyDetailFragment);
}
