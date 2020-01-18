package in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.gridcomponent;

import java.util.List;

import in.obvious.assignments.cosmos.client.foregroundcomponents.base.BaseViewController;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;

public interface GalaxyGridViewController extends BaseViewController {
    void showGalaxyList(List<Galaxy> galaxyList);

    void showLoading(boolean status);

    void showError(String errorMessage);
}
