package in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.gridcomponent;

import android.view.View;

import java.util.List;

import in.obvious.assignments.cosmos.client.foregroundcomponents.base.BaseFragment;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;

public class GalaxyGridFragment extends BaseFragment implements GalaxyGridViewController {

    public static GalaxyGridFragment getInstance() {
        return new GalaxyGridFragment();
    }

    @Override
    protected void setUpArgumentData() {

    }

    @Override
    protected void setUpViews(View view) {

    }

    @Override
    protected void setUpPresenter() {

    }

    @Override
    protected void onSetUpCompleted() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showGalaxyList(List<Galaxy> galaxyList) {

    }

    @Override
    public void showLoading() {

    }
}
