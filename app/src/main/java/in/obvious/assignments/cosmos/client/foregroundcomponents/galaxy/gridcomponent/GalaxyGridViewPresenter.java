package in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.gridcomponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
        galaxyViewModel.getGalaxyListRequestObservable().observe(galaxyGridViewController.getLifeCycleOwner(), this::consumeGalaxyListResponse);
        galaxyViewModel.getGalaxyList();
    }

    public void onRetryClicked() {
        galaxyViewModel.getGalaxyList();
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
                    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Collections.sort(galaxyListRequest.getData(), new Comparator<Galaxy>() {
                        @Override
                        public int compare(Galaxy galaxy2, Galaxy galaxy1) {
                            try {
                                Date date1 = sdf.parse(galaxy1.getDate());
                                Date date2 = sdf.parse(galaxy2.getDate());
                                return date1.compareTo(date2);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return 0;
                        }
                    });
                    galaxyGridViewController.showGalaxyList(galaxyListRequest.getData());
                }
            }
            break;
            case FAILED: {
                /*
                 * We can further classify error into various error according to error codes, in ui or in repository or
                 * at any convenient place.*/
                galaxyGridViewController.showLoading(false);
                galaxyGridViewController.showError(galaxyListRequest.getDomainRequestError().getErrorMessage());
            }
            break;
        }
    }

}
