package in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.detailscomponent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import in.obvious.assignments.cosmos.client.foregroundcomponents.base.BaseFragment;
import in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.GalaxyViewModel;
import in.obvious.assignments.cosmos.databinding.FragmentGalaxyDetailBinding;
import in.obvious.assignments.cosmos.databinding.ItemViewPagerGalaxyDetailsBinding;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;
import in.obvious.assignments.cosmos.framework.application.CosmosApplication;
import in.obvious.assignments.cosmos.framework.domainprovider.ViewDomainProvider;

public class GalaxyDetailFragment extends BaseFragment implements GalaxyDetailViewController {

    @Inject
    ViewDomainProvider viewDomainProvider;

    private FragmentGalaxyDetailBinding fragmentGalaxyDetailBinding;
    private GalaxyDetailPresenter galaxyDetailPresenter;
    private GalaxyViewModel galaxyViewModel;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager2 viewPager;
    private int selectedPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentGalaxyDetailBinding = FragmentGalaxyDetailBinding.inflate(inflater, container, false);
        return getView() != null ? getView() : fragmentGalaxyDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        CosmosApplication.getViewModelComponent().doInjection(this);
        galaxyViewModel = ViewModelProviders.of(this, viewDomainProvider).get(GalaxyViewModel.class);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setUpArgumentData() {
        selectedPosition = getArguments().getInt("selectedPosition");
    }

    @Override
    protected void setUpViews(View view) {
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager = fragmentGalaxyDetailBinding.galaxyDetailViewPager;
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    protected void setUpPresenter() {
        galaxyDetailPresenter = new GalaxyDetailPresenter(galaxyViewModel, this);
    }

    @Override
    protected void onSetUpCompleted() {

    }

    @Override
    public void addGalaxyListToViewPager(List<Galaxy> galaxyList) {
        viewPagerAdapter.setGalaxyList(galaxyList);
        viewPager.setCurrentItem(selectedPosition);
    }

    @Override
    public void showLoading(boolean status) {

    }

    @Override
    public void showError(String message) {

    }

    class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.GalaxyDetailsViewHolder> {

        private List<Galaxy> galaxyList;

        public ViewPagerAdapter() {
            galaxyList = new ArrayList<>();
        }

        void setGalaxyList(List<Galaxy> galaxyList) {
            this.galaxyList = galaxyList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public GalaxyDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ItemViewPagerGalaxyDetailsBinding binding = ItemViewPagerGalaxyDetailsBinding.inflate(getLayoutInflater(), parent, false);
            return new GalaxyDetailsViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(GalaxyDetailsViewHolder holder, int position) {
            Galaxy galaxy = galaxyList.get(position);
            holder.bind(galaxy);
            Glide.with(holder.itemView).load(galaxy.getUrl()).into(holder.itemViewPagerGalaxyDetailsBinding.galaxyImageView);
        }

        @Override
        public int getItemCount() {
            if (galaxyList != null) {
                return galaxyList.size();
            } else {
                return 0;
            }
        }


        public class GalaxyDetailsViewHolder extends RecyclerView.ViewHolder {

            private final ItemViewPagerGalaxyDetailsBinding itemViewPagerGalaxyDetailsBinding;

            GalaxyDetailsViewHolder(@NonNull ItemViewPagerGalaxyDetailsBinding itemViewPagerGalaxyDetailsBinding) {
                super(itemViewPagerGalaxyDetailsBinding.getRoot());
                this.itemViewPagerGalaxyDetailsBinding = itemViewPagerGalaxyDetailsBinding;
            }

            void bind(Galaxy galaxy) {
                itemViewPagerGalaxyDetailsBinding.setGalaxy(galaxy);
                itemViewPagerGalaxyDetailsBinding.executePendingBindings();
            }
        }

    }
}
