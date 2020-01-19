package in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.detailscomponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.GalaxyViewModel;
import in.obvious.assignments.cosmos.domain.DomainRequest;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;

public class GalaxyDetailPresenter {
    private GalaxyViewModel galaxyViewModel;
    private GalaxyDetailViewController galaxyDetailViewController;

    public GalaxyDetailPresenter(GalaxyViewModel galaxyViewModel, GalaxyDetailViewController galaxyGridViewController) {
        this.galaxyViewModel = galaxyViewModel;
        this.galaxyDetailViewController = galaxyGridViewController;
        galaxyViewModel.getGalaxyListRequestObservable().observe(galaxyGridViewController.getLifeCycleOwner(), this::consumeGalaxyListResponse);
        galaxyViewModel.getGalaxyList();
    }

    public void onRetryClicked() {
        galaxyViewModel.getGalaxyList();
    }

    private void consumeGalaxyListResponse(DomainRequest<List<Galaxy>> galaxyListRequest) {

        switch (galaxyListRequest.getCurrentState()) {
            case LOADING: {
                galaxyDetailViewController.showLoading(true);
                if (galaxyListRequest.getData() != null) {
                    galaxyDetailViewController.addGalaxyListToViewPager(galaxyListRequest.getData());
                }
            }
            break;
            case SUCCEED: {
                galaxyDetailViewController.showLoading(false);
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
                    galaxyDetailViewController.addGalaxyListToViewPager(galaxyListRequest.getData());
                }
            }
            break;
            case FAILED: {
                /*
                 * We can further classify error into various error according to error codes, in ui or in repository or
                 * at any convenient place.*/
                galaxyDetailViewController.showLoading(false);
                galaxyDetailViewController.showError(galaxyListRequest.getDomainRequestError().getErrorMessage());
            }
            break;
        }
    }

}