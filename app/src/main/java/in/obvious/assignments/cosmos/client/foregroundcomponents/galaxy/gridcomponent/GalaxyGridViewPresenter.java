package in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.gridcomponent;

import java.util.List;

import in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.GalaxyViewModel;
import in.obvious.assignments.cosmos.domain.DomainRequest;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;

public class GalaxyGridViewPresenter {
    private GalaxyViewModel galaxyViewModel;
    private GalaxyGridViewController galaxyGridViewController;

    public GalaxyGridViewPresenter(GalaxyViewModel galaxyViewModel, GalaxyGridViewController galaxyGridViewController) {
        this.galaxyViewModel = galaxyViewModel;
        this.galaxyGridViewController = galaxyGridViewController;
        galaxyViewModel.getGalaxyList().observe(galaxyGridViewController.getLifeCycleOwner(), this::consumeGalaxyListResponse);
    }

    private void consumeGalaxyListResponse(DomainRequest<List<Galaxy>> galaxyListRequest) {

        switch (galaxyListRequest.getCurrentState()) {
            case LOADING: {
                galaxyGridViewController.showLoading(true);
                if (galaxyListRequest.getData() != null) {
                    galaxyGridViewController.showGalaxyList(galaxyListRequest.getData());
                }
            }
            break;
            case SUCCEED: {
                galaxyGridViewController.showLoading(false);
                if (galaxyListRequest.getData() != null) {
                    galaxyGridViewController.showGalaxyList(galaxyListRequest.getData());
                }
            }
            break;
            case FAILED: {
                /*
                 * We can further classify error into various error according to error codes, in ui or in repository or
                 * at any convenient place.*/
                galaxyGridViewController.showError(galaxyListRequest.getDomainRequestError().getErrorMessage());
            }
            break;
        }
    }

}
