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
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import in.obvious.assignments.cosmos.client.foregroundcomponents.base.BaseFragment;
import in.obvious.assignments.cosmos.client.foregroundcomponents.galaxy.GalaxyViewModel;
import in.obvious.assignments.cosmos.databinding.FragmentGalaxyDetailBinding;
import in.obvious.assignments.cosmos.databinding.ItemViewPagerGalaxyDetailsBinding;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;
import in.obvious.assignments.cosmos.framework.application.CosmosApplication;
import in.obvious.assignments.cosmos.framework.viewmodelfactory.ViewModelFactory;

/*
 * Fragment which hosts details view of galaxy. Has ViewPager implemented so that we can swipe through galaxies
 * to get details of other galaxies which going back and selecting other galaxy to get details. */
public class GalaxyDetailFragment extends BaseFragment implements GalaxyDetailViewController {

    @Inject
    ViewModelFactory viewModelFactory;

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

    /*
     * Get ViewModel from the factory and store it for this session*/
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        CosmosApplication.getViewModelComponent().doInjection(this);
        galaxyViewModel = ViewModelProviders.of(this, viewModelFactory).get(GalaxyViewModel.class);
        super.onViewCreated(view, savedInstanceState);
    }

    /*
     * Callback for retrieving arguments from previous fragment and storing it for this screen.*/
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

    /*
     * Now everything in the ui has been set up. It's time for presenter to control things. We pass
     * view model, view controller and lifecycle owner to the presenter to observe data on view model.*/
    @Override
    protected void setUpPresenter() {
        galaxyDetailPresenter = new GalaxyDetailPresenter(galaxyViewModel, this);
    }

    @Override
    protected void onSetUpCompleted() {

    }

    /*
     * Setting list to view pager*/
    @Override
    public void addGalaxyListToViewPager(List<Galaxy> galaxyList) {
        viewPagerAdapter.setGalaxyList(galaxyList);
        viewPager.setCurrentItem(selectedPosition, false);
    }

    /*
     * Showing loading on request execution.*/
    @Override
    public void showLoading(boolean status) {
        if (status)
            fragmentGalaxyDetailBinding.galaxyDetailsProgressBar.setVisibility(View.VISIBLE);
        else
            fragmentGalaxyDetailBinding.galaxyDetailsProgressBar.setVisibility(View.GONE);
    }

    /*
     * If there is some error retrieving data from network or even from database then we will
     * show error to the user. Right now the errors are very much technical which can be made ofcourse be
     * simpler for user.*/
    @Override
    public void showError(String message) {
        Snackbar snackbar = Snackbar
                .make(fragmentGalaxyDetailBinding.getRoot(), message, Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", view -> galaxyDetailPresenter.onRetryClicked());
        snackbar.show();
    }

    /*
     * Adapter for view pager to show galaxy details on swiping right or left*/
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
            Glide.with(holder.itemView).load(galaxy.getHdurl()).into(holder.itemViewPagerGalaxyDetailsBinding.galaxyImageView);
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
