package in.obvious.assignments.cosmos.client.foregroundcomponents.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

public abstract class BaseFragment extends Fragment implements BaseViewController {


    /*
     * WayFindingConfiguration Let's you interact with host activity of the current fragment.
     *
     * If you want to do some changes in activity from fragment if they are not provided by system then we can use this.*/
    protected WayFindingConfiguration wayfindingConfiguration;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof WayFindingConfiguration) {
            wayfindingConfiguration = (WayFindingConfiguration) context;
        } else {
            throw new IllegalArgumentException("Please provide WayFindingConfiguration.");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null)
            setUpArgumentData();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews(view);
        setUpPresenter();
        onSetUpCompleted();
    }

    /*
     * Receive Arguments from previous fragment/component. */
    abstract protected void setUpArgumentData();

    /*
     * Set up views's initial state. */
    abstract protected void setUpViews(View view);

    /*
     * Set up presenter. */
    abstract protected void setUpPresenter();

    /*
    Set up Completed
    */
    abstract protected void onSetUpCompleted();

    @Override
    public void onPause() {
        super.onPause();
    }


    /*
     * We need to provide current view's lifecycle because if we get back observers would get attached to fragment
     * which doesn't completely destroy when we move to another fragment, if we get back to this fragment
     * duplicate observers would get attached to single observable and this would lead to errors.*/

    @Override
    public LifecycleOwner getLifeCycleOwner() {
        return getViewLifecycleOwner();
    }
}
