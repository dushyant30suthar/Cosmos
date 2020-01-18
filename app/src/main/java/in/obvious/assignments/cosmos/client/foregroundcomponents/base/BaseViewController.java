package in.obvious.assignments.cosmos.client.foregroundcomponents.base;

import androidx.lifecycle.LifecycleOwner;

public interface BaseViewController {

    void showError(String message);

    LifecycleOwner getLifeCycleOwner();

}
