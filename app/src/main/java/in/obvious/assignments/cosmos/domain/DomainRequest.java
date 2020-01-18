package in.obvious.assignments.cosmos.domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*
 * Request object to represent current status of the request sent to domain layer.
 * This object wraps response */
public class DomainRequest<T> {
    private State currentState;
    private T data;
    private int statusCode;
    private RequestError requestError;

    private DomainRequest(@NonNull State currentStatus, @Nullable T data,
                          @Nullable RequestError requestError, int statusCode) {
        this.currentState = currentStatus;
        this.data = data;
        this.requestError = requestError;
        this.statusCode = statusCode;
    }

    public static <T> DomainRequest<T> succeed(@NonNull T data, int statusCode) {
        return new DomainRequest<>(State.SUCCEED, data, null, statusCode);
    }

    public static <T> DomainRequest<T> failed(RequestError requestError, @Nullable T data) {
        return new DomainRequest<>(State.FAILED, data, requestError, requestError.getStatusCode());
    }

    public static <T> DomainRequest<T> loading(@Nullable T data) {
        return new DomainRequest<>(State.LOADING, data, null, 0);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public State getCurrentState() {
        return currentState;
    }

    public T getData() {
        return data;
    }

    public RequestError getRequestError() {
        return requestError;
    }

    public enum State {

        LOADING,
        FAILED,
        SUCCEED
    }
}
