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

/*
 * Presenter basically has control of the view. It controls the view by making use of viewController interface.
 *
 * We observe values here so that ui can be made dumb and logic could be saperated by ui code.
 *
 * We can also use presenter to do ui related calculations i.e. calculations that has to be done after the data
 * has been retrieved. For example, In this project we sort images by date. We do this thing in presenter only.*/
public class GalaxyDetailPresenter {
    private GalaxyViewModel galaxyViewModel;
    private GalaxyDetailViewController galaxyDetailViewController;

    public GalaxyDetailPresenter(GalaxyViewModel galaxyViewModel, GalaxyDetailViewController galaxyGridViewController) {
        this.galaxyViewModel = galaxyViewModel;
        this.galaxyDetailViewController = galaxyGridViewController;
        galaxyViewModel.getGalaxyListRequestObservable().observe(galaxyGridViewController.getLifeCycleOwner(), this::consumeGalaxyListResponse);
        galaxyViewModel.getGalaxyList();
    }

    /*
     * If we get error in first attempt then we can let user retry executing request.*/
    public void onRetryClicked() {
        galaxyViewModel.getGalaxyList();
    }

    /*
     * If there is any change in value of the live data then this method will be called and we will act
     * accordingly with data.*/
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