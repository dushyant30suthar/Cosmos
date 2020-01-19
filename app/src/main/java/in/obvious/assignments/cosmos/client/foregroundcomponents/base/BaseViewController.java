package in.obvious.assignments.cosmos.client.foregroundcomponents.base;

import androidx.lifecycle.LifecycleOwner;

/*
 * These methods are needed for every fragment. Other common methods will be signatured here.*/
public interface BaseViewController {

    void showError(String message);

    LifecycleOwner getLifeCycleOwner();

}
