package in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.gridcomponent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import javax.inject.Inject;

import in.obvious.assignments.cosmos.client.foregroundcomponents.base.BaseFragment;
import in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.GalaxyViewModel;
import in.obvious.assignments.cosmos.databinding.FragmentGalaxyGridBinding;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;
import in.obvious.assignments.cosmos.framework.application.CosmosApplication;
import in.obvious.assignments.cosmos.framework.domainprovider.ViewDomainProvider;

public class GalaxyGridFragment extends BaseFragment implements GalaxyGridViewController {

    @Inject
    ViewDomainProvider viewDomainProvider;

    private FragmentGalaxyGridBinding fragmentGalaxyGridBinding;
    private GalaxyGridViewPresenter galaxyGridViewPresenter;
    private GalaxyViewModel galaxyViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentGalaxyGridBinding = FragmentGalaxyGridBinding.inflate(inflater, container, false);
        return getView() != null ? getView() : fragmentGalaxyGridBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CosmosApplication.getViewModelComponent().doInjection(this);
        galaxyViewModel = ViewModelProviders.of(this, viewDomainProvider).get(GalaxyViewModel.class);
    }

    @Override
    protected void setUpArgumentData() {

    }

    @Override
    protected void setUpViews(View view) {

    }

    @Override
    protected void setUpPresenter() {
        galaxyGridViewPresenter = new GalaxyGridViewPresenter(galaxyViewModel, this);
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
    public void showLoading(boolean status) {

    }

}
