package in.obvious.assignments.cosmos.domain;


import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Response;

/*
 * We control our data flow here. We decide from where data should be fetched depending upon certain conditions.*/

public abstract class DataFlowControl<ResultType> extends Observable<DomainRequest<ResultType>> {


    private static final String TAG = "DataFlowControl";

    private Observer<? super DomainRequest<ResultType>> requestObserver;


    /*
     * Here is the flow implemented. This series of statements decide the final data returned to ui.*/
    @Override
    protected void subscribeActual(Observer<? super DomainRequest<ResultType>> observer) {

        requestObserver = observer;

        requestObserver.onNext(DomainRequest.loading(null));

        ResultType resultFromDatabase = loadFromDatabase();

        if (shouldFetchData(resultFromDatabase)) {
            fetchFromNetwork(resultFromDatabase);
        } else {
            requestObserver.onNext(DomainRequest.succeed(resultFromDatabase, 0));
        }
        requestObserver.onComplete();
    }

    private void fetchFromNetwork(ResultType resultFromDatabase) {

        requestObserver.onNext(DomainRequest.loading(resultFromDatabase));

        Response<ResultType> response;

        ResultType dataFromNetwork;

        try {

            response = loadFromNetwork().execute();

            if (response.body() != null) {

                Log.d(TAG, "Request Success " + response.toString());

                dataFromNetwork = response.body();

                requestObserver.onNext(DomainRequest.succeed(dataFromNetwork, response.code()));

            } else if (response.errorBody() != null) {

                Log.e(TAG, "Request Failure " + response.errorBody());

                RequestError requestError = new Gson().fromJson(response.errorBody().string(),
                        RequestError.class);

                requestError.setStatusCode(response.code());

                onFetchFailed(new RequestErrorException(requestError));

            } else
                onFetchFailed(new Throwable("No Data Received"));

        } catch (IOException /*| JSONException*/ e) {
            e.printStackTrace();
            onFetchFailed(e);
        }

        /*ResultType processedData = processResponse(dataFromNetwork);

        if (processedData == null) {
            requestObserver.onNext(DataRequest.failed("Not Found", null));
        } else {
            clearPreviousData();
            saveDataToDatabase(processedData);
            resultFromDatabase = loadFromDatabase();
            requestObserver.onNext(DataRequest.succeed(resultFromDatabase));
        }*/
    }


    protected abstract ResultType loadFromDatabase();

    protected abstract Call<ResultType> loadFromNetwork();

    protected abstract boolean shouldFetchData(@Nullable ResultType data);

    protected abstract void saveDataToDatabase(ResultType data);

    protected abstract void clearPreviousData();

    protected abstract ResultType processResponse(ResultType response);

    private void onFetchFailed(Throwable throwable) {
        requestObserver.onError(throwable);
        requestObserver.onComplete();
    }
}
