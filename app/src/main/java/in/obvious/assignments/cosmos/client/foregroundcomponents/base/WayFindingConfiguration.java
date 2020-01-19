package in.obvious.assignments.cosmos.client.foregroundcomponents.base;

import androidx.appcompat.widget.Toolbar;

/*
 * Interface to establish communication between activity and fragment.
 * Used only in the case if there is limitation, otherwise we can make communication
 * by using standard methods available.*/
public interface WayFindingConfiguration {

    Toolbar getToolbar();

}
