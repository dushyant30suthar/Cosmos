package in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.gridcomponent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import in.obvious.assignments.cosmos.client.foregroundcomponents.base.BaseFragment;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;

public class GalaxyGridFragment extends BaseFragment implements GalaxyGridViewController {

    public static GalaxyGridFragment getInstance() {
        return new GalaxyGridFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
