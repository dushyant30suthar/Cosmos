package in.obvious.assignments.cosmos.domain;

import androidx.lifecycle.MutableLiveData;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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

            // IMPORTANT - note that we are fetching the original error message, and not the
            // exception which led to this catch

            domainRequestError = new DomainRequestError();
            domainRequestError.setErrorMessage(e.getMessage());

            responseFromDomain.setValue(DomainRequest.failed(domainRequestError, null));
        }
    }

    @Override
    public void onComplete() {

    }
}
