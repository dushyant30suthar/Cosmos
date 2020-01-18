package in.obvious.assignments.cosmos.domain;

import androidx.lifecycle.MutableLiveData;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/*
 * Generic observer observing DomainRequestObservable and delivers response from RxObservable to LiveData
 * which is being observed by ui.*/

public class DomainRequestObserver<ResultType> implements Observer<DomainRequest<ResultType>> {

    private MutableLiveData<DomainRequest<ResultType>> responseFromDomain;

    public DomainRequestObserver(MutableLiveData<DomainRequest<ResultType>> responseFromDomain) {
        this.responseFromDomain = responseFromDomain;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(DomainRequest<ResultType> baseResponseDataRequest) {
        responseFromDomain.setValue(baseResponseDataRequest);
    }

    @Override
    public void onError(Throwable e) {

        DomainRequestError domainRequestError;

        try {

            domainRequestError = ((DomainRequestErrorException) e).getDomainRequestError();

            responseFromDomain.setValue(DomainRequest.failed(domainRequestError, null));

        } catch (Exception ex) {

            domainRequestError = new DomainRequestError();
            domainRequestError.setErrorMessage(e.getMessage());

            responseFromDomain.setValue(DomainRequest.failed(domainRequestError, null));
        }
    }

    @Override
    public void onComplete() {

    }
}
