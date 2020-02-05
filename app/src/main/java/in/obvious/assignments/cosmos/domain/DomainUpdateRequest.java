package in.obvious.assignments.cosmos.domain;

import io.reactivex.Observable;
import io.reactivex.Observer;

public abstract class DomainUpdateRequest<ResultType> extends Observable<DomainRequest<ResultType>> {

    private static final String TAG = "DomainUpdateRequest";

    private Observer<? super DomainRequest<ResultType>> requestObserver;


    /*
     * Here is the flow implemented. This series of statements decide the final data returned to ui.*/
    @Override
    protected void subscribeActual(Observer<? super DomainRequest<ResultType>> observer) {

        requestObserver = observer;


        requestObserver.onNext(DomainRequest.loading(null));

        Throwable errorFromDatabase = updateData();

        if (errorFromDatabase == null) {
            requestObserver.onNext(DomainRequest.succeed(null, 0));
        } else {
            onFetchFailed(errorFromDatabase);
        }
        requestObserver.onComplete();
    }

    public abstract Throwable updateData();


    private void onFetchFailed(Throwable throwable) {
        requestObserver.onError(throwable);
    }

}
