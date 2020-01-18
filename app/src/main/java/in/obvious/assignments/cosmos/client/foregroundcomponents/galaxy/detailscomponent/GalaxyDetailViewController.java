package in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.detailscomponent;

import java.util.List;

import in.obvious.assignments.cosmos.client.foregroundcomponents.base.BaseViewController;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;

public interface GalaxyDetailViewController extends BaseViewController {
    void addGalaxyListToViewPager(List<Galaxy> galaxyList);

    void showLoading(boolean status);

    void showError(String errorMessage);
}
