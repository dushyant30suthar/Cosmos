package in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.gridcomponent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import in.obvious.assignments.cosmos.client.foregroundcomponents.base.BaseFragment;
import in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.GalaxyViewModel;
import in.obvious.assignments.cosmos.databinding.FragmentGalaxyGridBinding;
import in.obvious.assignments.cosmos.databinding.ItemGalaxyGridBinding;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;
import in.obvious.assignments.cosmos.framework.application.CosmosApplication;
import in.obvious.assignments.cosmos.framework.domainprovider.ViewDomainProvider;

public class GalaxyGridFragment extends BaseFragment implements GalaxyGridViewController {

    @Inject
    ViewDomainProvider viewDomainProvider;

    private FragmentGalaxyGridBinding fragmentGalaxyGridBinding;
    private GalaxyGridViewPresenter galaxyGridViewPresenter;
    private GalaxyViewModel galaxyViewModel;
    private RecyclerView recyclerView;
    private GalaxyListAdapter galaxyListAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentGalaxyGridBinding = FragmentGalaxyGridBinding.inflate(inflater, container, false);
        return getView() != null ? getView() : fragmentGalaxyGridBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        CosmosApplication.getViewModelComponent().doInjection(this);
        galaxyViewModel = ViewModelProviders.of(this, viewDomainProvider).get(GalaxyViewModel.class);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setUpArgumentData() {

    }

    @Override
    protected void setUpViews(View view) {
        recyclerView = fragmentGalaxyGridBinding.galaxyListRecyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        galaxyListAdapter = new GalaxyListAdapter() {
            @Override
            void onGalaxyItemClicked(int galaxyId) {

            }
        };
        recyclerView.setAdapter(galaxyListAdapter);
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
        galaxyListAdapter.setGalaxyList(galaxyList);
    }

    @Override
    public void showLoading(boolean status) {
        if (status)
            fragmentGalaxyGridBinding.galaxyListProgressBar.setVisibility(View.VISIBLE);
        else
            fragmentGalaxyGridBinding.galaxyListProgressBar.setVisibility(View.GONE);
    }


    public abstract class GalaxyListAdapter extends RecyclerView.Adapter<GalaxyListAdapter.GalaxyListViewHolder> {

        List<Galaxy> galaxyList;

        GalaxyListAdapter() {
            this.galaxyList = new ArrayList<>();
        }

        void setGalaxyList(List<Galaxy> galaxyList) {
            this.galaxyList = galaxyList;
            notifyDataSetChanged();
        }

        abstract void onGalaxyItemClicked(int galaxyId);

        @NonNull
        @Override
        public GalaxyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            ItemGalaxyGridBinding binding = ItemGalaxyGridBinding.inflate(getLayoutInflater(), parent, false);
            return new GalaxyListViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull GalaxyListViewHolder holder, int position) {
            Galaxy galaxy = galaxyList.get(position);
            holder.galaxyItemBinding.getRoot().setOnClickListener(v -> onGalaxyItemClicked(galaxy.getGalaxyId()));
            holder.bind(galaxy);
            Glide.with(holder.itemView).load(galaxy.getUrl()).into(holder.galaxyItemBinding.galaxyImageView);
        }

        @Override
        public int getItemCount() {
            if (galaxyList != null) {
                return galaxyList.size();
            } else {
                return 0;
            }
        }


        class GalaxyListViewHolder extends RecyclerView.ViewHolder {

            private final ItemGalaxyGridBinding galaxyItemBinding;

            GalaxyListViewHolder(@NonNull ItemGalaxyGridBinding galaxyItemBinding) {
                super(galaxyItemBinding.getRoot());
                this.galaxyItemBinding = galaxyItemBinding;
            }

            void bind(Galaxy galaxy) {
                galaxyItemBinding.setGalaxy(galaxy);
                galaxyItemBinding.executePendingBindings();
            }
        }
    }
}
